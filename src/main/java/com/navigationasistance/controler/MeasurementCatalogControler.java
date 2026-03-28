package com.navigationasistance.controler;

import com.navigationasistance.modelo.MeasurementCatalog;
import com.navigationasistance.service.MeasurementCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurement_catalog")
public class MeasurementCatalogControler {

    @Autowired
    private MeasurementCatalogService service;

    @GetMapping("/listar")
    public ResponseEntity<List<MeasurementCatalog>> listar() {
        try {
            List<MeasurementCatalog> lista = service.listar();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarClave/{measurementName}")
    public ResponseEntity<MeasurementCatalog> listarClave(@PathVariable String measurementName) {
        try {
            MeasurementCatalog obj = service.listarClave(measurementName);
            if (obj != null) {
                return new ResponseEntity<>(obj, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/agregar")
    public String add(@RequestBody MeasurementCatalog obj, Model model) {
        int r = service.add(obj);
        if (r == 0) {
            return "No se pudo Registrar!";
        }
        return "Se registró con éxito!";
    }

    @PostMapping("/actualizar/{measurementName}")
    public String save(@RequestBody MeasurementCatalog obj, @PathVariable String measurementName, Model model) {
        obj.setMeasurementName(measurementName);
        int r = service.upd(obj);
        if (r == 0) {
            return "No se pudo Actualizar!";
        }
        return "Se actualizó con éxito!";
    }

    @PostMapping("/eliminar/{measurementName}")
    public String delete(@PathVariable String measurementName, Model model) {
        int r = service.del(measurementName);
        if (r == 0) {
            return "Registro No Eliminado!";
        }
        return "Registro Eliminado!";
    }
}