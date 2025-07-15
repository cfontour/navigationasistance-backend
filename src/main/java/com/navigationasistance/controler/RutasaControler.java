package com.navigationasistance.controler;

import com.navigationasistance.modelo.Rutasa;
import com.navigationasistance.service.RutasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rutasa")
public class RutasaControler {

    @Autowired
    private RutasaService rutasaService;

    @GetMapping("/listar")
    public List<Rutasa> listar() {
        return rutasaService.listar();
    }

    @GetMapping("/listarId/{id}")
    public Rutasa listarId(@PathVariable("id") Integer id) {
        return rutasaService.listarId(id);
    }

    @PostMapping("/agregar")
    public int agregar(@RequestBody Rutasa r) {
        return rutasaService.add(r);
    }

    @PutMapping("/editar")
    public int editar(@RequestBody Rutasa r) {
        return rutasaService.upd(r);
    }

    @DeleteMapping("/eliminar/{id}")
    public int eliminar(@PathVariable("id") Integer id) {
        return rutasaService.del(id);
    }

    @GetMapping("/color/{id}")
    public String obtenerColor(@PathVariable("id") Integer id) {
        Rutasa rutasa = rutasaService.listarId(id);
        return rutasa != null ? rutasa.getColor() : "desconocido";
    }

}
