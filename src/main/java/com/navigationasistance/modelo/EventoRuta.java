package com.navigationasistance.modelo;

import java.sql.Timestamp;

public class EventoRuta {
    private int id;
    private int rutaId;
    private String usuarioId;
    private Integer senialId; // puede ser null
    private String evento;
    private Timestamp fecha;

    // ===== CONSTRUCTORES =====

    public EventoRuta() {}

    public EventoRuta(int id, int rutaId, String usuarioId, Integer senialId, String evento, Timestamp fecha) {
        this.id = id;
        this.rutaId = rutaId;
        this.usuarioId = usuarioId;
        this.senialId = senialId;
        this.evento = evento;
        this.fecha = fecha;
    }

    // ===== GETTERS Y SETTERS =====

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRutaId() {
        return rutaId;
    }

    public void setRutaId(int rutaId) {
        this.rutaId = rutaId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getSenialId() {
        return senialId;
    }

    public void setSenialId(Integer senialId) {
        this.senialId = senialId;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
}
