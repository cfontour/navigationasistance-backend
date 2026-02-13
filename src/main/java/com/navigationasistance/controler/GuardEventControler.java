package com.navigationasistance.controler;

import com.navigationasistance.modelo.GuardEvent;
import com.navigationasistance.service.GuardEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guardEvent")

public class GuardEventControler {

    @Autowired
    private GuardEventService service;

    @GetMapping("/listar")
    public ResponseEntity<List<GuardEvent>> listar() {
        try {
            List<GuardEvent> lista = service.listar();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarId/{id}")
    public ResponseEntity<GuardEvent> listarId(@PathVariable Integer id) {
        try {
            GuardEvent guardEvent = service.listarId(id);
            if (guardEvent != null) {
                return new ResponseEntity<>(guardEvent, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/agregar")
    public String addTypeEvent(@RequestBody GuardEvent g,Model model) {
        int id=service.addGuardEvent(g);
        if(id==0) {
            return "No se pudo Regsitrar!";
        }
        return "Se registró con éxito!";
    }

    @PostMapping("/actualizar/{id}")
    public String save(@RequestBody GuardEvent g, @PathVariable Integer id, Model model) {
        g.setId(id);
        int r=service.updGuardEvent(g);
        if(r==0) {
            return "No se pudo Actualizar!";
        }
        return "Se actualizó con éxito!";
    }
    @PostMapping("/eliminar/{id}")
    public String delete(@PathVariable Integer id,Model model) {
        int r=service.delGuardEvent(id);
        if(r==0) {
            return "Registro No Eliminado!";
        }
        return "Registro Eliminado!";
    }

}
