package com.navigationasistance.controler;

import com.navigationasistance.modelo.UsuariocaPuntosControl;
import com.navigationasistance.service.UsuariocaPuntosControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuariocapuntoscontrol")
public class UsuariocaPuntosControlControler {

    @Autowired
    private UsuariocaPuntosControlService service;

    @PostMapping("/agregar")
    public int agregar(@RequestBody UsuariocaPuntosControl u) {
        return service.save(u);
    }

    @GetMapping("/listar")
    public List<UsuariocaPuntosControl> listar() {
        return service.listar();
    }

    @GetMapping("/listarPorNadadorrutaId/{id}")
    public List<UsuariocaPuntosControl> listarPorNadadorrutaId(@PathVariable("id") Integer id) {
        return service.listarPorNadadorrutaId(id);
    }
}
