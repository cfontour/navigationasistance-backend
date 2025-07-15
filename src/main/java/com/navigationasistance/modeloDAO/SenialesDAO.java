package com.navigationasistance.modeloDAO;

public class SenialesDAO {

    private Integer Id;
    private Integer ruta_id;
    private Integer mts;
    private Float latl;
    private Float lngl;
    private Float latr;
    private Float lngr;
    private Float latc;
    private Float lngc;
    private String tipo;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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
