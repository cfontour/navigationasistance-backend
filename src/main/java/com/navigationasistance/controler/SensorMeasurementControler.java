package com.navigationasistance.controler;

import com.navigationasistance.modelo.SensorMeasurement;
import com.navigationasistance.service.SensorMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
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

    @GetMapping("/listarUltimo/{devEui}/{measurementName}")
    public ResponseEntity<SensorMeasurement> listarUltimoPorDevEuiYMeasurementName(
            @PathVariable String devEui,
            @PathVariable String measurementName) {
        try {
            SensorMeasurement obj = service.listarUltimoPorDevEuiYMeasurementName(devEui, measurementName);
            if (obj != null) {
                return new ResponseEntity<>(obj, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
            System.out.println("Payload recibido: " + ttnPayload);

            Map<String, Object> data = (Map<String, Object>) ttnPayload.get("data");
            if (data == null) {
                data = ttnPayload;
            }

            Map<String, Object> endDeviceIds = (Map<String, Object>) data.get("end_device_ids");
            if (endDeviceIds == null) {
                return ResponseEntity.ok(Map.of("success", false, "message", "Sin end_device_ids"));
            }

            String deviceId = endDeviceIds.get("device_id") != null ? endDeviceIds.get("device_id").toString() : null;
            String devEui = endDeviceIds.get("dev_eui") != null ? endDeviceIds.get("dev_eui").toString() : null;
            String joinEui = endDeviceIds.get("join_eui") != null ? endDeviceIds.get("join_eui").toString() : null;

            if (deviceId == null || !deviceId.startsWith("geotraser-s2100")) {
                System.out.println("Dispositivo ignorado en webhook S2100: " + deviceId);
                return ResponseEntity.ok(Map.of(
                        "success", false,
                        "message", "Dispositivo no es S2100: " + deviceId
                ));
            }

            Map<String, Object> uplinkMessage = (Map<String, Object>) data.get("uplink_message");
            if (uplinkMessage == null) {
                return ResponseEntity.ok(Map.of("success", false, "message", "Sin uplink_message"));
            }

            Map<String, Object> decodedPayload = (Map<String, Object>) uplinkMessage.get("decoded_payload");
            if (decodedPayload == null) {
                return ResponseEntity.ok(Map.of("success", false, "message", "Sin decoded_payload"));
            }

            Timestamp receivedAt = null;
            Object receivedAtObj = data.get("received_at");
            if (receivedAtObj != null) {
                receivedAt = Timestamp.from(Instant.parse(receivedAtObj.toString()));
            }

            Double rssi = null;
            Double snr = null;
            String gatewayId = null;

            java.math.BigDecimal battery = null;
            Map<String, Object> lastBatteryPercentage = (Map<String, Object>) uplinkMessage.get("last_battery_percentage");
            if (lastBatteryPercentage != null && lastBatteryPercentage.get("value") != null) {
                battery = new java.math.BigDecimal(lastBatteryPercentage.get("value").toString());
            }

            List<Map<String, Object>> rxMetadata = (List<Map<String, Object>>) uplinkMessage.get("rx_metadata");
            if (rxMetadata != null && !rxMetadata.isEmpty()) {
                Map<String, Object> rx = rxMetadata.get(0);

                if (rx.get("rssi") != null) {
                    rssi = ((Number) rx.get("rssi")).doubleValue();
                }

                if (rx.get("snr") != null) {
                    snr = ((Number) rx.get("snr")).doubleValue();
                }

                Map<String, Object> gwIds = (Map<String, Object>) rx.get("gateway_ids");
                if (gwIds != null && gwIds.get("gateway_id") != null) {
                    gatewayId = gwIds.get("gateway_id").toString();
                }
            }

            List<List<Map<String, Object>>> messages =
                    (List<List<Map<String, Object>>>) decodedPayload.get("messages");

            if (messages == null || messages.isEmpty()) {
                return ResponseEntity.ok(Map.of("success", false, "message", "Sin messages en decoded_payload"));
            }

            int totalInsertados = 0;
            int totalIgnorados = 0;
            int totalErrores = 0;

            for (List<Map<String, Object>> group : messages) {
                if (group == null || group.isEmpty()) {
                    continue;
                }

                for (Map<String, Object> element : group) {
                    try {
                        if (element == null || element.isEmpty()) {
                            totalIgnorados++;
                            continue;
                        }

                        Object measurementIdObj = element.get("measurementId");
                        Object measurementValueObj = element.get("measurementValue");
                        Object typeObj = element.get("type");
                        Object unitObj = element.get("unit");

                        if (measurementIdObj == null || measurementValueObj == null) {
                            System.out.println("Elemento ignorado por faltar measurementId o measurementValue: " + element);
                            totalIgnorados++;
                            continue;
                        }

                        SensorMeasurement sm = new SensorMeasurement();
                        sm.setDeviceId(deviceId);
                        sm.setDevEui(devEui);
                        sm.setJoinEui(joinEui);
                        sm.setReceivedAt(receivedAt != null ? receivedAt.toLocalDateTime() : null);
                        sm.setRssi(rssi != null ? new java.math.BigDecimal(rssi.toString()) : null);
                        sm.setSnr(snr != null ? new java.math.BigDecimal(snr.toString()) : null);
                        sm.setGatewayId(gatewayId);
                        sm.setBattery(battery);
                        sm.setChannel("default");
                        sm.setDeltaNumeric(java.math.BigDecimal.ZERO);

                        sm.setMeasurementId(Integer.valueOf(measurementIdObj.toString()));
                        sm.setMeasurementName(typeObj != null ? typeObj.toString() : null);
                        sm.setUnit(unitObj != null ? unitObj.toString() : null);

                        String rawValue = measurementValueObj.toString();
                        try {
                            sm.setValueNumeric(new java.math.BigDecimal(rawValue));
                            sm.setValueText(null);
                        } catch (NumberFormatException ex) {
                            sm.setValueNumeric(null);
                            sm.setValueText(rawValue);
                        }

                        int r = service.add(sm);
                        totalInsertados += r;

                        System.out.println(
                                "Insert: measurementId=" + measurementIdObj +
                                        ", measurementName=" + sm.getMeasurementName() +
                                        ", value=" + rawValue +
                                        ", resultado=" + r
                        );

                    } catch (Exception exElemento) {
                        totalErrores++;
                        System.err.println("Error procesando elemento de messages: " + element);
                        exElemento.printStackTrace();
                    }
                }
            }

            System.out.println("=== RESUMEN WEBHOOK S2100 ===");
            System.out.println("deviceId: " + deviceId);
            System.out.println("insertados: " + totalInsertados);
            System.out.println("ignorados: " + totalIgnorados);
            System.out.println("errores: " + totalErrores);
            System.out.println("=============================");

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Webhook S2100 procesado",
                    "deviceId", deviceId,
                    "insertados", totalInsertados,
                    "ignorados", totalIgnorados,
                    "errores", totalErrores
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