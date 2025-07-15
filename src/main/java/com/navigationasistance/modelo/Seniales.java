package com.navigationasistance.modelo;

import javax.persistence.*;

@Table(name = "seniales")
@Entity
public class Seniales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true)
    private Integer ruta_id;

    @Column(nullable = true)
    private Integer mts;

    @Column(nullable = true)
    private Float latl;

    @Column(nullable = true)
    private Float lngl;

    @Column(nullable = true)
    private Float latr;

    @Column(nullable = true)
    private Float lngr;

    @Column(nullable = false)
    private Float latc;

    @Column(nullable = false)
    private Float lngc;

    @Column(nullable = true)
    private String tipo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRuta_id() {
        return ruta_id;
    }

    public void setRuta_id(Integer ruta_id) {
        this.ruta_id = ruta_id;
    }

    public Integer getMts() {
        return mts;
    }

    public void setMts(Integer mts) {
        this.mts = mts;
    }

    public Float getLatl() {
        return latl;
    }

    public void setLatl(Float latl) {
        this.latl = latl;
    }

    public Float getLngl() {
        return lngl;
    }

    public void setLngl(Float lngl) {
        this.lngl = lngl;
    }

    public Float getLatr() {
        return latr;
    }

    public void setLatr(Float latr) {
        this.latr = latr;
    }

    public Float getLngr() {
        return lngr;
    }

    public void setLngr(Float lngr) {
        this.lngr = lngr;
    }

    public Float getLatc() {
        return latc;
    }

    public void setLatc(Float latc) {
        this.latc = latc;
    }

    public Float getLngc() {
        return lngc;
    }

    public void setLngc(Float lngc) {
        this.lngc = lngc;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
