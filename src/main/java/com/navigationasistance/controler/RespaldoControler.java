package com.navigationasistance.controler;

import com.navigationasistance.modelo.Respaldo;
import com.navigationasistance.modeloDAO.RespaldoDAO;
import com.navigationasistance.service.RespaldoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/respaldo")
public class RespaldoControler {

    @Autowired
    private RespaldoService service;

    public RespaldoControler() {}

    @GetMapping("/listar")
    public ResponseEntity<List<Respaldo>> listar() {
        try {
            List<Respaldo> lista = service.listar();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarPorUsuario/{usuarioId}")
    public ResponseEntity<List<Respaldo>> listarPorUsuario(@PathVariable String usuarioId) {
        try {
            List<Respaldo> lista = service.listarPorUsuario(usuarioId);
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> addRespaldo(@RequestBody Respaldo r) {
        try {
            int id = service.addRespaldo(r);
            if (id == 0) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo registrar");
            }
            return ResponseEntity.ok("Se registró con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar: " + e.getMessage());
        }
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@RequestBody Respaldo r, @PathVariable Long id, Model model) {
        r.setId(id);
        int res = service.updRespaldo(r);
        if (res == 0) {
            return "No se pudo actualizar!";
        }
        return "Se actualizó con éxito!";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, Model model) {
        int r = service.delRespaldo(id);
        if (r == 0) {
            return "Registro no eliminado!";
        }
        return "Registro eliminado!";
    }
}
