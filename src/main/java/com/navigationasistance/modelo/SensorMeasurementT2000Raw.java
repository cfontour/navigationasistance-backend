package com.navigationasistance.modelo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "sensor_measurement_t2000_raw")
public class SensorMeasurementT2000Raw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "dev_eui")
    private String devEui;

    @Column(name = "join_eui")
    private String joinEui;

    @Column(name = "dev_addr")
    private String devAddr;

    @Column(name = "received_at")
    private Timestamp receivedAt;

    @Column(name = "f_port")
    private Integer fPort;

    @Column(name = "f_cnt")
    private Integer fCnt;

    @Column(name = "rssi")
    private Double rssi;

    @Column(name = "snr")
    private Double snr;

    @Column(name = "frequency")
    private Long frequency;

    @Column(name = "gateway_id")
    private String gatewayId;

    @Column(name = "payload", columnDefinition = "TEXT")
    private String payload;

    @Column(name = "decoded_payload", columnDefinition = "TEXT")
    private String decodedPayload;

    @Column(name = "frm_payload", columnDefinition = "TEXT")
    private String frmPayload;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public SensorMeasurementT2000Raw() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDevAddr() {
        return devAddr;
    }

    public void setDevAddr(String devAddr) {
        this.devAddr = devAddr;
    }

    public Timestamp getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(Timestamp receivedAt) {
        this.receivedAt = receivedAt;
    }

    public Integer getFPort() {
        return fPort;
    }

    public void setFPort(Integer fPort) {
        this.fPort = fPort;
    }

    public Integer getFCnt() {
        return fCnt;
    }

    public void setFCnt(Integer fCnt) {
        this.fCnt = fCnt;
    }

    public Double getRssi() {
        return rssi;
    }

    public void setRssi(Double rssi) {
        this.rssi = rssi;
    }

    public Double getSnr() {
        return snr;
    }

    public void setSnr(Double snr) {
        this.snr = snr;
    }

    public Long getFrequency() {
        return frequency;
    }

    public void setFrequency(Long frequency) {
        this.frequency = frequency;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getDecodedPayload() {
        return decodedPayload;
    }

    public void setDecodedPayload(String decodedPayload) {
        this.decodedPayload = decodedPayload;
    }

    public String getFrmPayload() {
        return frmPayload;
    }

    public void setFrmPayload(String frmPayload) {
        this.frmPayload = frmPayload;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}