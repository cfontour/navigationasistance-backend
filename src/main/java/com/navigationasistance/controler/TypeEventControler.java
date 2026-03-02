package com.navigationasistance.controler;

import com.navigationasistance.modelo.TypeEvent;
import com.navigationasistance.service.TypeEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/typeEvent")
public class TypeEventControler {

    @Autowired
    private TypeEventService service;

    @GetMapping("/listar")
    public ResponseEntity<List<TypeEvent>> listar() {
        try {
            List<TypeEvent> lista = service.listar();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarId/{id}")
    public ResponseEntity<TypeEvent> listarId(@PathVariable BigDecimal id) {
        try {
            TypeEvent typeEvent = service.listarId(id);
            if (typeEvent != null) {
                return new ResponseEntity<>(typeEvent, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/agregar")
    public String addTypeEvent(@RequestBody TypeEvent t,Model model) {
        int id=service.addTypeEvent(t);
        if(id==0) {
            return "No se pudo Regsitrar!";
        }
        return "Se registró con éxito!";
    }

    @PostMapping("/actualizar/{id}")
    public String save(@RequestBody TypeEvent t, @PathVariable BigDecimal id, Model model) {
        t.setId(id);
        int r=service.updTypeEvent(t);
        if(r==0) {
            return "No se pudo Actualizar!";
        }
        return "Se actualizó con éxito!";
    }
    @PostMapping("/eliminar/{id}")
    public String delete(@PathVariable BigDecimal id,Model model) {
        int r=service.delTypeEvent(id);
        if(r==0) {
            return "Registro No Eliminado!";
        }
        return "Registro Eliminado!";
    }

}
