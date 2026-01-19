package com.navigationasistance.controler;

import com.navigationasistance.modelo.Parametro;
import com.navigationasistance.service.ParametroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parametros")
public class ParametroControler {

    @Autowired
    private ParametroService service;

    @GetMapping("/listar")
    public ResponseEntity<List<Parametro>> listar() {
        try {
            List<Parametro> lista = service.listar();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarClave/{clave}")
    public ResponseEntity<Parametro> listarClave(@PathVariable String clave) {
        try {
            Parametro parametro = service.listarClave(clave);
            if (parametro != null) {
                return new ResponseEntity<>(parametro, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/agregar")
    public String addParametro(@RequestBody Parametro p,Model model) { //save(@RequestBody Persona p,Model model)
        int id=service.addParametro(p);
        if(id==0) {
            return "No se pudo Regsitrar!";
        }
        return "Se registró con éxito!";
    }

    @PostMapping("/actualizar/{clave}")
    public String save(@RequestBody Parametro p,@PathVariable String clave,Model model) {
        p.setClave(clave);
        int r=service.updParametro(p);
        if(r==0) {
            return "No se pudo Actualizar!";
        }
        return "Se actualizó con éxito!";
    }
    @PostMapping("/eliminar/{clave}")
    public String delete(@PathVariable String clave,Model model) {
        int r=service.delParametro(clave);
        if(r==0) {
            return "Registro No Eliminado!";
        }
        return "Registro Eliminado!";
    }

}
