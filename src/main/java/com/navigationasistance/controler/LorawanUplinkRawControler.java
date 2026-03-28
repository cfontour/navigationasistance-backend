package com.navigationasistance.controler;

import com.navigationasistance.modelo.LorawanUplinkRaw;
import com.navigationasistance.service.LorawanUplinkRawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lorawan_uplink_raw")
public class LorawanUplinkRawControler {

    @Autowired
    private LorawanUplinkRawService service;

    @GetMapping("/listar")
    public ResponseEntity<List<LorawanUplinkRaw>> listar() {
        try {
            List<LorawanUplinkRaw> lista = service.listar();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarClave/{devEui}")
    public ResponseEntity<LorawanUplinkRaw> listarClave(@PathVariable String devEui) {
        try {
            LorawanUplinkRaw obj = service.listarClave(devEui);
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
    public String add(@RequestBody LorawanUplinkRaw obj, Model model) {
        int r = service.add(obj);
        if (r == 0) {
            return "No se pudo Registrar!";
        }
        return "Se registró con éxito!";
    }

    @PostMapping("/actualizar/{devEui}")
    public String save(@RequestBody LorawanUplinkRaw obj, @PathVariable String devEui, Model model) {
        obj.setDevEui(devEui);
        int r = service.upd(obj);
        if (r == 0) {
            return "No se pudo Actualizar!";
        }
        return "Se actualizó con éxito!";
    }

    @PostMapping("/eliminar/{devEui}")
    public String delete(@PathVariable String devEui, Model model) {
        int r = service.del(devEui);
        if (r == 0) {
            return "Registro No Eliminado!";
        }
        return "Registro Eliminado!";
    }
}