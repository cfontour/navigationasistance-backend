package com.navigationasistance.controler;

import com.navigationasistance.modelo.Sensor;
import com.navigationasistance.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensor")
public class SensorControler {

    @Autowired
    SensorService service;

    @GetMapping("/listar")
    public List<Sensor> listar() {
        return service.listar();
    }

    @GetMapping("/listarId/{sensorId}")
    public Sensor listarId(@PathVariable String sensorId) {
        return service.listarId(sensorId);
    }

    @PostMapping("/agregar")
    public int agregar(@RequestBody Sensor s) {
        return service.addSensor(s);
    }

    @PutMapping("/actualizar")
    public int actualizar(@RequestBody Sensor s) {
        return service.updSensor(s);
    }

    @DeleteMapping("/eliminar/{sensorId}")
    public int eliminar(@PathVariable String sensorId) {
        return service.delSensor(sensorId);
    }
}