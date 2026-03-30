package com.navigationasistance.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "sensor_measurement")
public class SensorMeasurement {

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

    @Column(name = "channel", length = 50)
    private String channel;

    @Column(name = "measurement_id", nullable = false, insertable = false, updatable = false)
    private Integer measurementId;

    @Column(name = "measurement_name", length = 150)
    private String measurementName;

    @Column(name = "value_numeric", precision = 18, scale = 6)
    private BigDecimal valueNumeric;

    @Column(name = "value_text", length = 255)
    private String valueText;

    @Column(name = "unit", length = 50)
    private String unit;

    @Column(name = "delta_numeric", precision = 18, scale = 6)
    private BigDecimal deltaNumeric;

    @Column(name = "battery", precision = 10, scale = 2)
    private BigDecimal battery;

    @Column(name = "rssi", precision = 10, scale = 2)
    private BigDecimal rssi;

    @Column(name = "snr", precision = 10, scale = 2)
    private BigDecimal snr;

    @Column(name = "gateway_id", length = 100)
    private String gatewayId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uplink_raw_id", nullable = false)
    private LorawanUplinkRaw uplinkRaw;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_id", referencedColumnName = "measurement_id", nullable = false)
    private MeasurementCatalog measurementCatalog;

    public SensorMeasurement() {
    }

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now(ZoneId.of("America/Montevideo"));
        }
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setMeasurementId(Integer measurementId) {
        this.measurementId = measurementId;
    }

    public Integer getMeasurementId() {
        return measurementId;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public void setMeasurementName(String measurementName) {
        this.measurementName = measurementName;
    }

    public BigDecimal getValueNumeric() {
        return valueNumeric;
    }

    public void setValueNumeric(BigDecimal valueNumeric) {
        this.valueNumeric = valueNumeric;
    }

    public String getValueText() {
        return valueText;
    }

    public void setValueText(String valueText) {
        this.valueText = valueText;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getDeltaNumeric() {
        return deltaNumeric;
    }

    public void setDeltaNumeric(BigDecimal deltaNumeric) {
        this.deltaNumeric = deltaNumeric;
    }

    public BigDecimal getBattery() {
        return battery;
    }

    public void setBattery(BigDecimal battery) {
        this.battery = battery;
    }

    public BigDecimal getRssi() {
        return rssi;
    }

    public void setRssi(BigDecimal rssi) {
        this.rssi = rssi;
    }

    public BigDecimal getSnr() {
        return snr;
    }

    public void setSnr(BigDecimal snr) {
        this.snr = snr;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LorawanUplinkRaw getUplinkRaw() {
        return uplinkRaw;
    }

    public void setUplinkRaw(LorawanUplinkRaw uplinkRaw) {
        this.uplinkRaw = uplinkRaw;
    }

    public MeasurementCatalog getMeasurementCatalog() {
        return measurementCatalog;
    }

    public void setMeasurementCatalog(MeasurementCatalog measurementCatalog) {
        this.measurementCatalog = measurementCatalog;
    }
}
