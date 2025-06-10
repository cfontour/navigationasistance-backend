package com.navigationasistance.controler;

import com.navigationasistance.modelo.RutasPuntos;
import com.navigationasistance.modeloDAO.RutasPuntosDAO;
import com.navigationasistance.service.RutasPuntosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rutaspuntos")
public class RutasPuntosControler {

    @Autowired
    private RutasPuntosService service;

    @GetMapping("/listar")
    public List<RutasPuntos> listar() {
        return service.listar();
    }

    @GetMapping("/listar/{rutaId}")
    public List<RutasPuntosDAO> listarPorRuta(@PathVariable Integer rutaId) {
        return service.listarPorRuta(rutaId);
    }

    @PostMapping("/agregar")
    public RutasPuntos agregar(@RequestBody RutasPuntos rp) {
        service.addRutasPuntos(rp);
        return rp;
    }

    @PostMapping("/agregar-masivo")
    public List<RutasPuntos> agregarMasivo(@RequestBody List<RutasPuntos> puntos) {
        return service.addMultiplesRutasPuntos(puntos);
    }

    @PutMapping("/actualizar")
    public RutasPuntos actualizar(@RequestBody RutasPuntos rp) {
        service.updRutasPuntos(rp);
        return rp;
    }

    @GetMapping("/buscar/{id}")
    public RutasPuntos buscarPorId(@PathVariable Integer id) {
        return service.findById(id);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.delRutasPuntos(id);
    }
}
