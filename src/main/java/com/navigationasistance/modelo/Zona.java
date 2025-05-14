package com.navigationasistance.modelo;

import javax.persistence.*;

@Table(name = "zonas")
@Entity
public class Zona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private String zona;
    @Column
    private String lato;
    @Column
    private String lngo;
    @Column
    private String latd;
    @Column
    private String lngd;
    @Column
    private String nomo;
    @Column
    private String nomd;

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getLato() {
        return lato;
    }

    public void setLato(String lato) {
        this.lato = lato;
    }

    public String getLngo() {
        return lngo;
    }

    public void setLngo(String lngo) {
        this.lngo = lngo;
    }

    public String getLatd() {
        return latd;
    }

    public void setLatd(String latd) {
        this.latd = latd;
    }

    public String getLngd() {
        return lngd;
    }

    public void setLngd(String lngd) {
        this.lngd = lngd;
    }

    public String getNomo() {
        return nomo;
    }

    public void setNomo(String nomo) {
        this.nomo = nomo;
    }

    public String getNomd() {
        return nomd;
    }

    public void setNomd(String nomd) {
        this.nomd = nomd;
    }
}
