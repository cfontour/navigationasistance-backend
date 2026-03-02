package com.navigationasistance.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "guard_event")
@Entity
public class GuardEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String usuario_id;
    @Column
    private BigDecimal localidad_id;
    @Column
    private BigDecimal type_id;
    @Column
    private String event_descripcion;
    @Column
    private byte[] event_image;
    @Column
    private LocalDateTime event_datetime;

    public GuardEvent() {
        // TODO Auto-generated constructor stub
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    public BigDecimal getLocalidad_id() {
        return localidad_id;
    }

    public void setLocalidad_id(BigDecimal localidad_id) {
        this.localidad_id = localidad_id;
    }

    public BigDecimal getType_id() {
        return type_id;
    }

    public void setType_id(BigDecimal type_id) {
        this.type_id = type_id;
    }

    public String getEvent_descripcion() {
        return event_descripcion;
    }

    public void setEvent_descripcion(String event_descripcion) {
        this.event_descripcion = event_descripcion;
    }

    public byte[] getEvent_image() {
        return event_image;
    }

    public void setEvent_image(byte[] event_image) {
        this.event_image = event_image;
    }

    public LocalDateTime getEvent_datetime() {
        return event_datetime;
    }

    public void setEvent_datetime(LocalDateTime event_datetime) {
        this.event_datetime = event_datetime;
    }
}
