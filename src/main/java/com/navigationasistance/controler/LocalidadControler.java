package com.navigationasistance.controler;

import com.navigationasistance.modelo.Localidad;
import com.navigationasistance.service.LocalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localidad")
public class LocalidadControler {

    @Autowired
    private LocalidadService service;

    @GetMapping("/listar")
    public ResponseEntity<List<Localidad>> listar() {
        try {
            List<Localidad> lista = service.listar();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarId/{id}")
    public ResponseEntity<Localidad> listarId(@PathVariable Integer id) {
        try {
            Localidad localidad = service.listarId(id);
            if (localidad != null) {
                return new ResponseEntity<>(localidad, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/agregar")
    public String addLocalidad(@RequestBody Localidad l,Model model) {
        int id=service.addLocalidad(l);
        if(id==0) {
            return "No se pudo Regsitrar!";
        }
        return "Se registró con éxito!";
    }

    @PostMapping("/actualizar/{id}")
    public String save(@RequestBody Localidad l,@PathVariable Integer id,Model model) {
        l.setId(id);
        int r=service.updLocalidad(l);
        if(r==0) {
            return "No se pudo Actualizar!";
        }
        return "Se actualizó con éxito!";
    }
    @PostMapping("/eliminar/{id}")
    public String delete(@PathVariable Integer id,Model model) {
        int r=service.delLocalidad(id);
        if(r==0) {
            return "Registro No Eliminado!";
        }
        return "Registro Eliminado!";
    }

}
