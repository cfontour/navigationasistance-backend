package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.Rutas;

import java.util.List;

public interface RutasInterface {
    List<Rutas> listar();
    Rutas listarId(Integer id);
    int add(Rutas r);
    int upd(Rutas r);
    int del(Integer id);
}
