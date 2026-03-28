package com.navigationasistance.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "lorawan_uplink_raw")
public class LorawanUplinkRaw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "received_at", nullable = false)
    private LocalDateTime receivedAt;

    @Column(name = "device_id", length = 100)
    private String deviceId;

    @Column(name = "dev_eui", nullable = false, length = 32)
    private String devEui;

    @Column(name = "join_eui", length = 32)
    private String joinEui;

    @Column(name = "application_id", length = 100)
    private String applicationId;

    @Column(name = "gateway_id", length = 100)
    private String gatewayId;

    @Column(name = "f_port")
    private Integer fPort;

    @Column(name = "frm_payload", columnDefinition = "TEXT")
    private String frmPayload;

    @Column(name = "decoded_payload_json", columnDefinition = "TEXT")
    private String decodedPayloadJson;

    @Column(name = "raw_json", nullable = false, columnDefinition = "TEXT")
    private String rawJson;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "uplinkRaw", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SensorMeasurement> measurements;

    public LorawanUplinkRaw() {
    }

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDevEui() {
        return devEui;
    }

    public void setDevEui(String devEui) {
        this.devEui = devEui;
    }

    public String getJoinEui() {
        return joinEui;
    }

    public void setJoinEui(String joinEui) {
        this.joinEui = joinEui;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public Integer getFPort() {
        return fPort;
    }

    public void setFPort(Integer fPort) {
        this.fPort = fPort;
    }

    public String getFrmPayload() {
        return frmPayload;
    }

    public void setFrmPayload(String frmPayload) {
        this.frmPayload = frmPayload;
    }

    public String getDecodedPayloadJson() {
        return decodedPayloadJson;
    }

    public void setDecodedPayloadJson(String decodedPayloadJson) {
        this.decodedPayloadJson = decodedPayloadJson;
    }

    public String getRawJson() {
        return rawJson;
    }

    public void setRawJson(String rawJson) {
        this.rawJson = rawJson;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<SensorMeasurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<SensorMeasurement> measurements) {
        this.measurements = measurements;
    }
}
