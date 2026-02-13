package com.navigationasistance.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "typeEvent")
@Entity
public class TypeEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String type_nombre;
    @Column
    private Integer type_importancia;

    public TypeEvent() {
        // TODO Auto-generated constructor stub
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType_nombre() {
        return type_nombre;
    }

    public void setType_nombre(String type_nombre) {
        this.type_nombre = type_nombre;
    }

    public Integer getType_importancia() {
        return type_importancia;
    }

    public void setType_importancia(Integer type_importancia) {
        this.type_importancia = type_importancia;
    }
}
