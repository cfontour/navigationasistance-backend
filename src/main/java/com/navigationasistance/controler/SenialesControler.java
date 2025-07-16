package com.navigationasistance.controler;

import com.navigationasistance.modelo.Rutas;
import com.navigationasistance.modelo.Seniales;
import com.navigationasistance.service.RutasService;
import com.navigationasistance.service.SenialesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seniales")
public class SenialesControler {

    @Autowired
    private SenialesService senialesService;

    @GetMapping("/listar")
    public List<Seniales> listar() {
        return senialesService.listar();
    }

    @GetMapping("/listarId/{id}")
    public Seniales listarId(@PathVariable("id") Integer id) {
        return senialesService.listarId(id);
    }

    @GetMapping("/getSenialesByRutaId/{rutaId}")
    public Seniales getSenialesByRutaId(@PathVariable("rutaId") Integer rutaId) {
        return senialesService.getSenialesByRutaId(rutaId);
    }

    @PostMapping("/agregar")
    public int agregar(@RequestBody Seniales s) {
        return senialesService.addSeniales(s);
    }

    @PutMapping("/editar")
    public int editar(@RequestBody Seniales s) {
        return senialesService.updSeniales(s);
    }

    @DeleteMapping("/eliminar/{id}")
    public int eliminar(@PathVariable("id") Integer id) {
        return senialesService.delSeniales(id);
    }

    @DeleteMapping("/eliminarRutaId/{rutaId}")
    public int eliminarRutaId(@PathVariable("rutaId") Integer rutaId) {
        return senialesService.delSenialesPorRutaId(rutaId);
    }

}
