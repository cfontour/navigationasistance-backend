package com.navigationasistance.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.navigationasistance.modelo.NadadorPosicion;
import com.navigationasistance.service.NadadorposicionService;

@RestController
@RequestMapping(path = "/nadadorposicion", produces = MediaType.APPLICATION_JSON_VALUE)
public class NadadorposicionControler {

    @Autowired
    NadadorposicionService service;

    @GetMapping("/listar")
    public ResponseEntity<List<NadadorPosicion>> listar() {
        try {
            List<NadadorPosicion> lista = service.listar();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getNadadorPosicion/{id}")
    public ResponseEntity<NadadorPosicion> getNadadorPosicion(@PathVariable String id) {
        try {
            NadadorPosicion nadadorPosicion = service.getNadadorPosicion(id);
            if (nadadorPosicion != null) {
                return new ResponseEntity<>(nadadorPosicion, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/agregar")
    public ResponseEntity<NadadorPosicion> upsert(@RequestBody NadadorPosicion n) {
        try {
            if (n == null || n.getUsuarioid() == null || n.getNadadorlat() == null || n.getNadadorlng() == null) {
                return ResponseEntity.badRequest().build();
            }

            int resultado = service.upsertNadador(n); // cambia de addNadador a upsertNadador

            return ResponseEntity.status(resultado == 1 ? HttpStatus.OK : HttpStatus.CREATED).body(n);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/actualizar/{id}")
    public ResponseEntity<NadadorPosicion> save(@RequestBody NadadorPosicion n, @PathVariable String id) {
        try {
            if (id == null || id.trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            if (n == null || n.getNadadorlat() == null || n.getNadadorlng() == null) {
                return ResponseEntity.badRequest().build();
            }

            n.setUsuarioid(id);
            int r = service.updNadador(n);

            if (r == 0) {
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
            }

            return ResponseEntity.ok(n);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            if (id == null || id.trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            int r = service.delNadador(id);
            if (r == 0) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}