package com.navigationasistance.controler;

import com.navigationasistance.modelo.Grupo;
import com.navigationasistance.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoControler {

    @Autowired
    private GrupoService service;

    @GetMapping("/listar")
    public ResponseEntity<List<Grupo>> listar() {
        try {
            List<Grupo> lista = service.listar();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarGrupo/{id}")
    public ResponseEntity<Grupo> listarGrupo(@PathVariable String id) {
        try {
            Grupo grupo = service.listarGrupo(id);
            if (grupo != null) {
                return new ResponseEntity<>(grupo, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/agregar")
    public String addGrupo(@RequestBody Grupo g,Model model) {
        int id=service.addGrupo(g);
        if(id==0) {
            return "No se pudo Regsitrar!";
        }
        return "Se registró con éxito!";
    }

    @PostMapping("/actualizar/{id}")
    public String save(@RequestBody Grupo g,@PathVariable String id,Model model) {
        g.setGrupoid(id);
        int r=service.updGrupo(g);
        if(r==0) {
            return "No se pudo Actualizar!";
        }
        return "Se actualizó con éxito!";
    }
    @PostMapping("/eliminar/{id}")
    public String delete(@PathVariable String id,Model model) {
        int r=service.delGrupo(id);
        if(r==0) {
            return "Registro No Eliminado!";
        }
        return "Registro Eliminado!";
    }

}
