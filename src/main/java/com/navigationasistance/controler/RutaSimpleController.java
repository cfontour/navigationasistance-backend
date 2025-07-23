package com.navigationasistance.controler;

import com.navigationasistance.modelo.Rutas;
import com.navigationasistance.modeloDAO.RutaSimpleDTO;
import com.navigationasistance.service.RutaSimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rutas")

public class RutaSimpleController {

    @Autowired
    private RutaSimpleService rutaService;

    @GetMapping("/listarSimples") // Puedes elegir el nombre que quieras, ej: /todas, /basic, /selector
    public ResponseEntity<List<RutaSimpleDTO>> listarRutasSimples() {
        List<RutaSimpleDTO> rutas = rutaService.listarTodasRutasSimples();
        return ResponseEntity.ok(rutas);
    }
}
