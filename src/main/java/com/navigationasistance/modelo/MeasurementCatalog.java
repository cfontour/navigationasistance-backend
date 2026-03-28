package com.navigationasistance.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "measurement_catalog")
public class MeasurementCatalog {

    @Id
    @Column(name = "measurement_id")
    private Integer measurementId;

    @Column(name = "measurement_name", nullable = false, length = 150)
    private String measurementName;

    @Column(name = "unit", length = 50)
    private String unit;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "measurementCatalog")
    private List<SensorMeasurement> measurements;

    public MeasurementCatalog() {
    }

    @PrePersist
    public void prePersist() {
        if (this.active == null) {
            this.active = true;
        }
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    public Integer getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(Integer measurementId) {
        this.measurementId = measurementId;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public void setMeasurementName(String measurementName) {
        this.measurementName = measurementName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
