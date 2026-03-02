package com.navigationasistance.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "typeevent")
@Entity
public class TypeEvent {
    @Id
    @Column
    private BigDecimal id;
    @Column
    private String type_nombre;
    @Column
    private BigDecimal type_importancia;

    public TypeEvent() {
        // TODO Auto-generated constructor stub
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getType_nombre() {
        return type_nombre;
    }

    public void setType_nombre(String type_nombre) {
        this.type_nombre = type_nombre;
    }

    public BigDecimal getType_importancia() {
        return type_importancia;
    }

    public void setType_importancia(BigDecimal type_importancia) {
        this.type_importancia = type_importancia;
    }
}
