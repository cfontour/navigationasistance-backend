package com.navigationasistance.controler;

import com.navigationasistance.modelo.NadadorRutas;
import com.navigationasistance.modeloDAO.NadadorRutasDAO;
import com.navigationasistance.service.NadadorRutasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nadadorrutas")
public class NadadorRutasControler {

    @Autowired
    private NadadorRutasService service;

    @GetMapping("/listar")
    public List<NadadorRutas> listar() {
        return service.listar();
    }

    @GetMapping("/buscar/{id}")
    public NadadorRutas buscarPorId(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping("/porusuario/{usuarioId}")
    public NadadorRutas buscarPorUsuario(@PathVariable String usuarioId) {
        return service.buscarPorUsuario(usuarioId);
    }

    @GetMapping("/porruta/{rutaId}")
    public List<NadadorRutasDAO> listarPorRuta(@PathVariable Integer rutaId) {
        return service.getPorRuta(rutaId);
    }

    @PostMapping("/agregar")
    public NadadorRutas agregar(@RequestBody NadadorRutas nr) {
        service.addNadadorRuta(nr);
        return nr;
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.delNadadorRuta(id);
    }
}
