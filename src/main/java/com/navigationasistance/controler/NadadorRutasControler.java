package com.navigationasistance.controler;

import com.navigationasistance.modelo.NadadorRutas;
import com.navigationasistance.service.NadadorRutasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nadadorrutas")
public class NadadorRutasControler {

    @Autowired
    private NadadorRutasService nadadorRutasService;

    @GetMapping("/listar")
    public List<NadadorRutas> listar() {
        return nadadorRutasService.listar();
    }

    @GetMapping("/listarId/{id}")
    public NadadorRutas listarId(@PathVariable("id") Integer id) {
        return nadadorRutasService.listarId(id);
    }

    @GetMapping("/buscarPorUsuario/{usuarioId}")
    public NadadorRutas buscarPorUsuario(@PathVariable("usuarioId") String usuarioId) {
        return nadadorRutasService.buscarPorUsuario(usuarioId);
    }

    @GetMapping("/listarPorRuta/{rutaId}")
    public List<NadadorRutas> listarPorRuta(@PathVariable("rutaId") Integer rutaId) {
        return nadadorRutasService.listarPorRuta(rutaId);
    }

    @PostMapping("/agregar")
    public int agregar(@RequestBody NadadorRutas nr) {
        return nadadorRutasService.add(nr);
    }

    @DeleteMapping("/eliminar/{id}")
    public int eliminar(@PathVariable("id") Integer id) {
        return nadadorRutasService.del(id);
    }

    @GetMapping("/colorPorUsuario/{usuarioId}")
    public String obtenerColorPorUsuario(@PathVariable("usuarioId") String usuarioId) {
        NadadorRutas nr = nadadorRutasService.buscarPorUsuario(usuarioId);
        return (nr != null && nr.getRuta() != null) ? nr.getRuta().getColor() : "desconocido";
    }
}
