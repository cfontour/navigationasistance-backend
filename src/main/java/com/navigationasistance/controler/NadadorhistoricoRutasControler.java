package com.navigationasistance.controler;

import com.navigationasistance.modelo.NadadorHistoricoRutas;
import com.navigationasistance.modelo.NadadorPosicion;
import com.navigationasistance.service.NadadorhistoricoRutasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/nadadorhistoricorutas", produces = MediaType.APPLICATION_JSON_VALUE)
public class NadadorhistoricoRutasControler {

    @Autowired
    NadadorhistoricoRutasService service;

    @GetMapping("/listar")
    public ResponseEntity<List<NadadorHistoricoRutas>> listar() {
        try {
            List<NadadorHistoricoRutas> lista = service.listar();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{usuarioId}/{fecha}")
    public ResponseEntity<List<NadadorHistoricoRutas>> obtenerPorUsuarioYFecha(
            @PathVariable String usuarioId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        try {
            List<NadadorHistoricoRutas> lista = service.obtenerPorUsuarioYFecha(usuarioId, fecha);
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/recorridos/{usuarioId}/{fecha}")
    public ResponseEntity<List<UUID>> obtenerRecorridosPorFecha(
            @PathVariable String usuarioId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        try {
            List<UUID> recorridos = service.obtenerRecorridosPorFecha(usuarioId, fecha);
            return ResponseEntity.ok(recorridos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/agregar")
    public ResponseEntity<?> upsert(@RequestBody NadadorHistoricoRutas r) {
        try {
            if (r == null || r.getUsuarioid() == null || r.getNadadorlat() == null ||
                    r.getNadadorlng() == null || r.getRecorridoid() == null) {
                return ResponseEntity.badRequest().body("Campos obligatorios faltantes");
            }

            int resultado = service.insertarRuta(r);

            if (resultado == 1) {
                return ResponseEntity.status(HttpStatus.CREATED).build(); // No devolvés el objeto sin id
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("No se pudo insertar el punto");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en el servidor: " + e.getMessage());
        }
    }

    @GetMapping("/ruta/{recorridoId}")
    public ResponseEntity<List<NadadorHistoricoRutas>> obtenerRutaPorRecorrido(@PathVariable UUID recorridoId) {
        try {
            List<NadadorHistoricoRutas> lista = service.obtenerPorRecorridoId(recorridoId);
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
