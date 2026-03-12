package com.navigationasistance.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "grupousuarios")
@Entity
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private String grupoid;
    @Column
    private String gruponombre;

    @Column
    private String grupodescripcion;

    public Grupo() {
        // TODO Auto-generated constructor stub
    }

    public String getGrupoid() {
        return grupoid;
    }

    public void setGrupoid(String grupoid) {
        this.grupoid = grupoid;
    }

    public String getGruponombre() {
        return gruponombre;
    }

    public void setGruponombre(String gruponombre) {
        this.gruponombre = gruponombre;
    }

    public String getGrupodescripcion() {
        return grupodescripcion;
    }

    public void setGrupodescripcion(String grupodescripcion) {
        this.grupodescripcion = grupodescripcion;
    }
}
