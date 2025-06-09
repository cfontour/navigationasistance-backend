package com.navigationasistance.controler;

import com.navigationasistance.modelo.Rutas;
import com.navigationasistance.service.RutasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rutas")
public class RutasControler {

    @Autowired
    private RutasService rutasService;

    @GetMapping("/listar")
    public List<Rutas> listar() {
        return rutasService.listar();
    }

    @GetMapping("/listarId/{id}")
    public Rutas listarId(@PathVariable("id") Integer id) {
        return rutasService.listarId(id);
    }

    @PostMapping("/agregar")
    public int agregar(@RequestBody Rutas r) {
        return rutasService.add(r);
    }

    @PutMapping("/editar")
    public int editar(@RequestBody Rutas r) {
        return rutasService.upd(r);
    }

    @DeleteMapping("/eliminar/{id}")
    public int eliminar(@PathVariable("id") Integer id) {
        return rutasService.del(id);
    }

    @GetMapping("/color/{id}")
    public String obtenerColor(@PathVariable("id") Integer id) {
        Rutas ruta = rutasService.listarId(id);
        return ruta != null ? ruta.getColor() : "desconocido";
    }
}
