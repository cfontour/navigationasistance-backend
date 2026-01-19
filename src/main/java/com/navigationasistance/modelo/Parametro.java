package com.navigationasistance.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Parametro")
@Entity
public class Parametro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private String clave;
    @Column
    private String valor;

    public Parametro() {
        // TODO Auto-generated constructor stub
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
