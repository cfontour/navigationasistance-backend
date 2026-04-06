package com.navigationasistance.controler;

import com.navigationasistance.modelo.SensorMeasurementT2000Raw;
import com.navigationasistance.service.SensorMeasurementT2000RawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/sensor_measurement_t2000_raw", produces = MediaType.APPLICATION_JSON_VALUE)
public class SensorMeasurementT2000RawControler {

    @Autowired
    SensorMeasurementT2000RawService service;

    @GetMapping("/listar")
    public ResponseEntity<List<SensorMeasurementT2000Raw>> listar() {
        try {
            List<SensorMeasurementT2000Raw> lista = service.listar();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/agregar")
    public ResponseEntity<SensorMeasurementT2000Raw> add(@RequestBody SensorMeasurementT2000Raw s) {
        try {
            if (s == null) {
                return ResponseEntity.badRequest().build();
            }

            int r = service.add(s);

            if (r == 0) {
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(s);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/eliminarTodos")
    public ResponseEntity<Void> eliminarTodos() {
        try {
            service.delAll();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/webhook-ttn-t2000")
    public ResponseEntity<?> webhookTtnT2000(@RequestBody Map<String, Object> ttnPayload) {
        try {
            SensorMeasurementT2000Raw s = new SensorMeasurementT2000Raw();

            Map<String, Object> endDeviceIds = (Map<String, Object>) ttnPayload.get("end_device_ids");
            if (endDeviceIds != null) {
                s.setDeviceId((String) endDeviceIds.get("device_id"));
                s.setDevEui((String) endDeviceIds.get("dev_eui"));
                s.setJoinEui((String) endDeviceIds.get("join_eui"));
                s.setDevAddr((String) endDeviceIds.get("dev_addr"));
            }

            Object receivedAtObj = ttnPayload.get("received_at");
            if (receivedAtObj != null) {
                s.setReceivedAt(Timestamp.from(Instant.parse(receivedAtObj.toString())));
            }

            Map<String, Object> uplinkMessage = (Map<String, Object>) ttnPayload.get("uplink_message");
            if (uplinkMessage != null) {
                Object fPortObj = uplinkMessage.get("f_port");
                if (fPortObj != null) {
                    s.setFPort(((Number) fPortObj).intValue());
                }

                Object fCntObj = uplinkMessage.get("f_cnt");
                if (fCntObj != null) {
                    s.setFCnt(((Number) fCntObj).intValue());
                }

                Object frmPayloadObj = uplinkMessage.get("frm_payload");
                if (frmPayloadObj != null) {
                    s.setFrmPayload(frmPayloadObj.toString());
                }

                Object decodedPayloadObj = uplinkMessage.get("decoded_payload");
                if (decodedPayloadObj != null) {
                    s.setDecodedPayload(decodedPayloadObj.toString());
                }

                Map<String, Object> settings = (Map<String, Object>) uplinkMessage.get("settings");
                if (settings != null) {
                    Object frequencyObj = settings.get("frequency");
                    if (frequencyObj != null) {
                        s.setFrequency(Long.valueOf(frequencyObj.toString()));
                    }
                }

                List<Map<String, Object>> rxMetadata = (List<Map<String, Object>>) uplinkMessage.get("rx_metadata");
                if (rxMetadata != null && !rxMetadata.isEmpty()) {
                    Map<String, Object> firstRx = rxMetadata.get(0);

                    Object rssiObj = firstRx.get("rssi");
                    if (rssiObj != null) {
                        s.setRssi(((Number) rssiObj).doubleValue());
                    }

                    Object snrObj = firstRx.get("snr");
                    if (snrObj != null) {
                        s.setSnr(((Number) snrObj).doubleValue());
                    }

                    Map<String, Object> gatewayIds = (Map<String, Object>) firstRx.get("gateway_ids");
                    if (gatewayIds != null) {
                        s.setGatewayId((String) gatewayIds.get("gateway_id"));
                    }
                }
            }

            s.setPayload(ttnPayload.toString());

            int resultado = service.add(s);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("resultado", resultado);
            response.put("deviceId", s.getDeviceId());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();

            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}