package com.navigationasistance.controler;

import com.navigationasistance.modelo.Zona;
import com.navigationasistance.service.ZonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zonas")
public class ZonaControler {

    @Autowired
    private ZonaService service;

    public ZonaControler() {
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Zona>> listar() {
        try {
            List<Zona> lista = service.listar();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarZona/{zon}")
    public ResponseEntity<Zona> listarZona(@PathVariable String zon) {
        try {
            Zona zona = service.listarZona(zon);
            if (zona != null) {
                return new ResponseEntity<>(zona, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getZonaByZona")
    public Zona getZonaByZona(@RequestParam String zona) {
        return service.getZonaByZona(zona);
    }

    @PostMapping("/agregar")
    public String addZona(@RequestBody Zona z, Model model) { //save(@RequestBody Persona p,Model model)
        int id = service.addZona(z);
        if (id == 0) {
            return "No se pudo Regsitrar!";
        }
        return "Se registró con éxito!";
    }

    @PostMapping("/actualizar/{zona}")
    public String save(@RequestBody Zona z, @PathVariable String zona, Model model) {
        z.setZona(zona);
        int r = service.updZona(z);
        if (r == 0) {
            return "No se pudo Actualizar!";
        }
        return "Se actualizó con éxito!";
    }

    @PostMapping("/eliminar/{zona}")
    public String delete(@PathVariable String zona, Model model) {
        int r = service.delZona(zona);
        if (r == 0) {
            return "Registro No Eliminado!";
        }
        return "Registro Eliminado!";
    }

}
