package com.navigationasistance.modelo;

import javax.persistence.*;

@Entity
@Table(name = "nadadorrutas", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ruta_id", "usuario_id"})
})
public class NadadorRutas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ruta_id", nullable = false)
    private Rutas ruta;

    @Column(name = "usuario_id", nullable = false)
    private String usuarioId;

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Rutas getRuta() { return ruta; }
    public void setRuta(Rutas ruta) { this.ruta = ruta; }

    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
}
