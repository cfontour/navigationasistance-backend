package com.navigationasistance.modelo;

import javax.persistence.*;

@Entity
@Table(name = "rutas_puntos")
public class RutasPuntos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ruta_id", nullable = false)
    private Rutas ruta;

    @Column(nullable = false)
    private Integer secuencia;

    @Column(nullable = false)
    private Double latitud;

    @Column(nullable = false)
    private Double longitud;

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Rutas getRuta() { return ruta; }
    public void setRuta(Rutas ruta) { this.ruta = ruta; }

    public Integer getSecuencia() { return secuencia; }
    public void setSecuencia(Integer secuencia) { this.secuencia = secuencia; }

    public Double getLatitud() { return latitud; }
    public void setLatitud(Double latitud) { this.latitud = latitud; }

    public Double getLongitud() { return longitud; }
    public void setLongitud(Double longitud) { this.longitud = longitud; }
}
