package com.navigationasistance.modeloDAO;

public class RutaSimpleDTO {
    private Integer id; // Correcto: Integer
    private String nombre;
    private String color;

    public RutaSimpleDTO(Integer id, String nombre, String color) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}