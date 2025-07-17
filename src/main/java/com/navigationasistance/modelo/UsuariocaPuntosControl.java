package com.navigationasistance.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarioca_puntoscontrol")
public class UsuariocaPuntosControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nadadorruta_id", nullable = false)
    private String nadadorrutaId;

    @Column(name = "punto_control", nullable = false)
    private String puntoControl;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "ruta_id", nullable = false)
    private Integer ruta_id;

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNadadorrutaId() { return nadadorrutaId; }
    public void setNadadorrutaId(String nadadorrutaId) { this.nadadorrutaId = nadadorrutaId; }

    public String getPuntoControl() { return puntoControl; }
    public void setPuntoControl(String puntoControl) { this.puntoControl = puntoControl; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public Integer getRuta_id() {
        return ruta_id;
    }

    public void setRuta_id(Integer ruta_id) {
        this.ruta_id = ruta_id;
    }
}
