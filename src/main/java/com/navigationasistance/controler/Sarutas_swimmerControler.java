package com.navigationasistance.controler;

import com.navigationasistance.modelo.Saruta_swimmer;
import com.navigationasistance.modeloDAO.Saruta_swimmerDAO;
import com.navigationasistance.service.Saruta_swimmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sarutas_siwmmer")
public class Sarutas_swimmerControler {

    @Autowired
    private Saruta_swimmerService service;

    @GetMapping("/listar")
    public List<Saruta_swimmer> listar() {
        return service.listar();
    }

    @GetMapping("/buscar/{id}")
    public Saruta_swimmer buscarPorId(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping("/porusuario/{usuarioId}")
    public Saruta_swimmer buscarPorUsuario(@PathVariable String usuarioId) {
        return service.buscarPorUsuario(usuarioId);
    }

    @GetMapping("/porruta/{rutaId}")
    public List<Saruta_swimmerDAO> listarPorRuta(@PathVariable Integer rutaId) {
        return service.getPorRuta(rutaId);
    }

    @PostMapping("/agregar")
    public Saruta_swimmer agregar(@RequestBody Saruta_swimmer nr) {
        service.addNadadorRuta(nr);
        return nr;
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.delNadadorRuta(id);
    }

}
