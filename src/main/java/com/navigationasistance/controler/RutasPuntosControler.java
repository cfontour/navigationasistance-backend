package com.navigationasistance.controler;

import com.navigationasistance.modelo.RutasPuntos;
import com.navigationasistance.service.RutasPuntosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rutaspuntos")
public class RutasPuntosControler {

    @Autowired
    private RutasPuntosService rutasPuntosService;

    @GetMapping("/listar")
    public List<RutasPuntos> listar() {
        return rutasPuntosService.listar();
    }

    @GetMapping("/listarId/{id}")
    public RutasPuntos listarId(@PathVariable("id") Integer id) {
        return rutasPuntosService.listarId(id);
    }

    @GetMapping("/listarPorRuta/{rutaId}")
    public List<RutasPuntos> listarPorRuta(@PathVariable("rutaId") Integer rutaId) {
        return rutasPuntosService.listarPorRuta(rutaId);
    }

    @PostMapping("/agregar")
    public int agregar(@RequestBody RutasPuntos punto) {
        return rutasPuntosService.add(punto);
    }

    @PutMapping("/editar")
    public int editar(@RequestBody RutasPuntos punto) {
        return rutasPuntosService.upd(punto);
    }

    @DeleteMapping("/eliminar/{id}")
    public int eliminar(@PathVariable("id") Integer id) {
        return rutasPuntosService.del(id);
    }
}
