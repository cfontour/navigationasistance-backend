package com.navigationasistance.modeloDAO;

// Ubicación: com.example.geotraser.dto (o similar)
public class RutaSimpleDTO {
    private Long id;
    private String nombre;
    private String color; // Asumiendo que 'color' es un campo de Ruta

    // Constructor
    public RutaSimpleDTO(Long id, String nombre, String color) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}