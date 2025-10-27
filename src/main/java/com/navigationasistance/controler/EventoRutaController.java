package com.navigationasistance.controler;

import com.navigationasistance.modelo.EventoRuta;
import com.navigationasistance.service.EventoRutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventoruta")
@CrossOrigin(origins = "*")
public class EventoRutaController {

    @Autowired
    private EventoRutaService service;

    @PostMapping("/agregar")
    public int agregar(@RequestBody EventoRuta eventoRuta) {
        return service.agregar(eventoRuta);
    }

    @GetMapping("/listar")
    public List<EventoRuta> listar() {
        return service.listar();
    }

    @GetMapping("/listar/usuario/{usuarioId}")
    public List<EventoRuta> listarPorUsuario(@PathVariable String usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }

    @GetMapping("/listar/ruta/{rutaId}")
    public List<EventoRuta> listarPorRuta(@PathVariable int rutaId) {
        return service.listarPorRuta(rutaId);
    }

    @PutMapping("/actualizar")
    public int actualizar(@RequestBody EventoRuta eventoRuta) {
        return service.actualizar(eventoRuta);
    }

    @DeleteMapping("/eliminar/{id}")
    public int eliminar(@PathVariable int id) {
        return service.eliminar(id);
    }

    @GetMapping("/buscar/{id}")
    public EventoRuta buscarPorId(@PathVariable int id) {
        return service.buscarPorId(id);
    }
}
