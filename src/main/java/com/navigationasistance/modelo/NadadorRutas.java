package com.navigationasistance.modelo;

import javax.persistence.*;

@Entity
@Table(name = "nadadorrutas")
public class NadadorRutas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "usuario_id", nullable = false)
    private String usuarioId;

    @Column(name = "ruta_id", nullable = false)
    private Integer rutaId;

    @Column(name = "grupoid", nullable = true)
    private String grupoid;

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }

    public Integer getRutaId() { return rutaId; }
    public void setRutaId(Integer rutaId) { this.rutaId = rutaId; }

    public String getGrupoid() {
        return grupoid;
    }

    public void setGrupoid(String grupoid) {
        this.grupoid = grupoid;
    }
}
