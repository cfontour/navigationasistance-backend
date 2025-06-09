package com.navigationasistance.modelo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rutas")
public class Rutas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String color;

    @OneToMany(mappedBy = "ruta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RutasPuntos> puntos;

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public List<RutasPuntos> getPuntos() { return puntos; }
    public void setPuntos(List<RutasPuntos> puntos) { this.puntos = puntos; }
}
