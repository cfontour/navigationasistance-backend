package com.navigationasistance.controler;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.navigationasistance.modelo.NadadorHistoricoRutas;
import com.navigationasistance.service.NadadorhistoricoRutasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.navigationasistance.modelo.NadadorPosicion;
import com.navigationasistance.service.NadadorposicionService;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/nadadorposicion", produces = MediaType.APPLICATION_JSON_VALUE)
public class NadadorposicionControler {

    @Autowired
    NadadorposicionService service;

    @Autowired
    private NadadorhistoricoRutasService historicoService;

    @GetMapping("/listar")
    public ResponseEntity<List<NadadorPosicion>> listar() {
        try {
            List<NadadorPosicion> lista = service.listar();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getNadadorPosicion/{id}")
    public ResponseEntity<NadadorPosicion> getNadadorPosicion(@PathVariable String id) {
        try {
            NadadorPosicion nadadorPosicion = service.getNadadorPosicion(id);
            if (nadadorPosicion != null) {
                return new ResponseEntity<>(nadadorPosicion, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarActivosEnCarrera")
    public List<NadadorPosicion> listarActivosEnCarrera() {
        return service.listarVinculadosANadadorRutas();
    }

    @PostMapping("/agregar")
    public ResponseEntity<NadadorPosicion> upsert(@RequestBody NadadorPosicion n) {
        System.out.println("=== DEBUG CONTROLLER ===");
        System.out.println("FECHA RECIBIDA: " + n.getFechaUltimaActualizacion());
        System.out.println("TIPO: " + (n.getFechaUltimaActualizacion() != null ? n.getFechaUltimaActualizacion().getClass() : "null"));
        System.out.println("========================");
        try {
            if (n == null || n.getUsuarioid() == null || n.getNadadorlat() == null || n.getNadadorlng() == null) {
                return ResponseEntity.badRequest().build();
            }

            int resultado = service.upsertNadador(n); // cambia de addNadador a upsertNadador

            return ResponseEntity.status(resultado == 1 ? HttpStatus.OK : HttpStatus.CREATED).body(n);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/actualizar/{id}")
    public ResponseEntity<NadadorPosicion> save(@RequestBody NadadorPosicion n, @PathVariable String id) {
        try {
            if (id == null || id.trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            if (n == null || n.getNadadorlat() == null || n.getNadadorlng() == null) {
                return ResponseEntity.badRequest().build();
            }

            n.setUsuarioid(id);
            int r = service.updNadador(n);

            if (r == 0) {
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
            }

            return ResponseEntity.ok(n);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/emergency/{id}")
    public ResponseEntity<?> updateEmergency(@PathVariable String id, @RequestBody Map<String, Boolean> body) {
        try {
            if (id == null || id.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("ID inválido");
            }

            if (!body.containsKey("emergency")) {
                return ResponseEntity.badRequest().body("Falta el campo 'emergency'");
            }

            boolean emergency = body.get("emergency");
            int r = service.updEmergency(id, emergency);

            if (r == 0) {
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("No se actualizó 'emergency'");
            }

            return ResponseEntity.ok().body("Campo 'emergency' actualizado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno");
        }
    }

    @PostMapping("/eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            if (id == null || id.trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            int r = service.delNadador(id);
            if (r == 0) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/webhook-ttn")
    public ResponseEntity<?> procesarWebhookTTN(@RequestBody Map<String, Object> ttnPayload) {
        try {
            // Log para debug
            System.out.println("=== DEBUG WEBHOOK TTN ===");
            System.out.println("Payload recibido: " + ttnPayload.toString());

            // Extraer datos del payload TTN
            Map<String, Object> uplinkMessage = (Map<String, Object>) ttnPayload.get("uplink_message");
            if (uplinkMessage == null) {
                return ResponseEntity.ok(Map.of("success", true, "message", "Sin uplink_message"));
            }

            Map<String, Object> decodedPayload = (Map<String, Object>) uplinkMessage.get("decoded_payload");
            if (decodedPayload == null) {
                return ResponseEntity.ok(Map.of("success", true, "message", "Sin decoded_payload"));
            }

            // Extraer device ID
            Map<String, Object> endDeviceIds = (Map<String, Object>) ttnPayload.get("end_device_ids");
            String deviceId = (String) endDeviceIds.get("device_id");

            // EXTRAER CAMPOS
            Integer frameId = (Integer) decodedPayload.get("frame_id");
            Integer bearing = (Integer) decodedPayload.get("bearing");
            Boolean emergency = (Boolean) decodedPayload.get("emergency");

            // SI HAY EMERGENCIA, procesar SIEMPRE (con o sin GPS)
            if (emergency != null && emergency) {
                NadadorPosicion emergencyUpdate = new NadadorPosicion();
                emergencyUpdate.setUsuarioid(deviceId);
                emergencyUpdate.setEmergency(true);

                int resultadoEmergency = service.updEmergency(deviceId, true);

                Map<String, Object> response = new HashMap<>();
                response.put("success", true);
                response.put("message", "EMERGENCIA PROCESADA");
                response.put("emergency", true);
                response.put("deviceId", deviceId);
                response.put("resultado", resultadoEmergency);

                return ResponseEntity.ok(response);
            }

            // Si NO hay emergencia, solo procesar si hay GPS
            if (frameId == null || frameId != 6) {
                return ResponseEntity.ok(Map.of("success", true, "message", "Frame sin GPS: " + frameId));
            }

            // Extraer coordenadas de locations
            Double latitude = null;
            Double longitude = null;
            Map<String, Object> locations = (Map<String, Object>) uplinkMessage.get("locations");
            if (locations != null) {
                Map<String, Object> frmPayload = (Map<String, Object>) locations.get("frm-payload");
                if (frmPayload != null) {
                    latitude = (Double) frmPayload.get("latitude");
                    longitude = (Double) frmPayload.get("longitude");
                }
            }

            if (latitude == null || longitude == null || latitude == 0.0 || longitude == 0.0) {
                return ResponseEntity.ok(Map.of("success", true, "message", "Coordenadas inválidas"));
            }

            // Convertir a String
            String latString = String.valueOf(latitude);
            String lngString = String.valueOf(longitude);

            // Crear NadadorPosicion usando tu estructura
            NadadorPosicion nadadorPosicion = new NadadorPosicion();
            nadadorPosicion.setUsuarioid(deviceId);
            nadadorPosicion.setNadadorlat(latString);
            nadadorPosicion.setNadadorlng(lngString);
            nadadorPosicion.setBearing(bearing);
            nadadorPosicion.setEmergency(emergency);

            // Usar tu service existente con upsert
            int resultado = service.upsertNadador(nadadorPosicion);

            System.out.println("Resultado upsert: " + resultado);
            System.out.println("========================");

            // 2. INSERTAR EN HISTÓRICO
            String dateKey = deviceId + "_" + LocalDate.now().toString();
            UUID recorridoId = UUID.nameUUIDFromBytes(dateKey.getBytes());

            NadadorHistoricoRutas historico = new NadadorHistoricoRutas();
            historico.setUsuarioid(deviceId);
            historico.setRecorridoid(recorridoId);

            ZoneId uruguayZone = ZoneId.of("America/Montevideo");
            ZonedDateTime nowUruguay = ZonedDateTime.now(uruguayZone);
            LocalDate fechaUruguay = nowUruguay.toLocalDate();
            Timestamp horaUruguay = Timestamp.valueOf(nowUruguay.toLocalDateTime());

            historico.setNadadorfecha(fechaUruguay);
            historico.setNadadorhora(horaUruguay);
            historico.setSecuencia(1);
            historico.setNadadorlat(latString);
            historico.setNadadorlng(lngString);

            int resultadoHistorico = historicoService.insertarRuta(historico);

            System.out.println("Resultado histórico: " + resultadoHistorico);
            System.out.println("========================");

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Posición procesada");
            response.put("resultado", resultado);
            response.put("deviceId", deviceId);
            response.put("lat", latString);
            response.put("lng", lngString);
            response.put("bearing", bearing);
            response.put("emergency", emergency);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.err.println("Error procesando webhook TTN: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.ok(Map.of("success", false, "error", e.getMessage()));
        }
    }

}