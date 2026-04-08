package com.navigationasistance.controler;

import com.navigationasistance.modelo.SensorMeasurement;
import com.navigationasistance.service.SensorMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sensor_measurement")
public class SensorMeasurementControler {

    @Autowired
    private SensorMeasurementService service;

    @GetMapping("/listar")
    public ResponseEntity<List<SensorMeasurement>> listar() {
        try {
            List<SensorMeasurement> lista = service.listar();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarClave/{devEui}")
    public ResponseEntity<List<SensorMeasurement>> listarClave(@PathVariable String devEui) {
        try {
            List<SensorMeasurement> lista = service.listarClave(devEui);
            if (lista != null && !lista.isEmpty()) {
                return new ResponseEntity<>(lista, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/flujo-s2100")
    public ResponseEntity<?> getFlujo() {
        return ResponseEntity.ok(service.listarFlujo());
    }

    @PostMapping("/agregar")
    public String add(@RequestBody SensorMeasurement obj, Model model) {
        int r = service.add(obj);
        if (r == 0) {
            return "No se pudo Registrar!";
        }
        return "Se registró con éxito!";
    }

    @PostMapping("/actualizar/{devEui}")
    public String save(@RequestBody SensorMeasurement obj, @PathVariable String devEui, Model model) {
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

    @PostMapping("/webhook-s2100-ttn")
    public ResponseEntity<?> procesarWebhookTTN(@RequestBody Map<String, Object> ttnPayload) {
        try {

            System.out.println("=== DEBUG WEBHOOK TTN S2100 ===");
            System.out.println("Payload recibido: " + ttnPayload.toString());

            Map<String, Object> uplinkMessage = (Map<String, Object>) ttnPayload.get("uplink_message");
            if (uplinkMessage == null) {
                return ResponseEntity.ok(Map.of("success", true, "message", "Sin uplink_message"));
            }

            Map<String, Object> decodedPayload = (Map<String, Object>) uplinkMessage.get("decoded_payload");
            if (decodedPayload == null) {
                return ResponseEntity.ok(Map.of("success", true, "message", "Sin decoded_payload"));
            }

            Map<String, Object> endDeviceIds = (Map<String, Object>) ttnPayload.get("end_device_ids");
            String deviceId = (String) endDeviceIds.get("device_id");
            String devEui = (String) endDeviceIds.get("dev_eui");
            String joinEui = (String) endDeviceIds.get("join_eui");

            Integer fPort = (Integer) uplinkMessage.get("f_port");

            System.out.println("Device: " + deviceId);
            System.out.println("Decoded payload: " + decodedPayload);

            // 🔥 ITERAR dinámicamente el payload
            for (Map.Entry<String, Object> entry : decodedPayload.entrySet()) {

                String key = entry.getKey();
                Object value = entry.getValue();

                SensorMeasurement sm = new SensorMeasurement();

                sm.setDeviceId(deviceId);
                sm.setDevEui(devEui);
                sm.setJoinEui(joinEui);
                sm.setMeasurementName(key);
                sm.setChannel("default");

                // valor numérico o texto
                if (value instanceof Number) {
                    sm.setValueNumeric(new java.math.BigDecimal(value.toString()));
                    sm.setDeltaNumeric(java.math.BigDecimal.ZERO);
                } else {
                    sm.setValueText(String.valueOf(value));
                }

                // extras si existen
                if ("battery".equalsIgnoreCase(key)) {
                    if (value instanceof Number) {
                        sm.setBattery(new java.math.BigDecimal(value.toString()));
                    }
                }

                // insertar
                int r = service.add(sm);

                System.out.println("Insert measurement: " + key + " = " + value + " → " + r);
            }

            System.out.println("===============================");

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Webhook S2100 procesado",
                    "deviceId", deviceId
            ));

        } catch (Exception e) {
            System.err.println("Error procesando webhook TTN S2100: " + e.getMessage());
            e.printStackTrace();

            return ResponseEntity.ok(Map.of(
                    "success", false,
                    "error", e.getMessage()
            ));
        }
    }
}