package com.navigationasistance.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "localidad")
@Entity
public class Localidad {
    @Id
    @Column
    private BigDecimal id;
    @Column
    private String localidad_pais;
    @Column
    private String localidad_nombre;

    public Localidad() {
        // TODO Auto-generated constructor stub
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getLocalidad_pais() {
        return localidad_pais;
    }

    public void setLocalidad_pais(String localidad_pais) {
        this.localidad_pais = localidad_pais;
    }

    public String getLocalidad_nombre() {
        return localidad_nombre;
    }

    public void setLocalidad_nombre(String localidad_nombre) {
        this.localidad_nombre = localidad_nombre;
    }

}
