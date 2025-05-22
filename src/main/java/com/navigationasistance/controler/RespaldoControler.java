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
    @ResponseBody
    public String addRespaldo(@RequestBody Respaldo r) {
        int id = service.addRespaldo(r);
        if (id == 0) {
            return "No se pudo registrar!";
        }
        return "Se registró con éxito!";
    }

    @PostMapping("/actualizar/{id}")
    @ResponseBody
    public String actualizar(@RequestBody Respaldo r, @PathVariable Long id) {
        Respaldo existente = service.findById(id);
        if (existente == null) {
            return "No se encontró el respaldo!";
        }

        // Solo actualizamos el campo contacto, el resto se mantiene
        existente.setContacto(r.getContacto());

        int res = service.updRespaldo(existente);
        return res == 0 ? "No se pudo actualizar!" : "Se actualizó con éxito!";
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
