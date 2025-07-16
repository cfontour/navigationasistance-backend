package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.Seniales;

import java.util.List;

public interface SenialesInterface {
    List<Seniales> listar();

    Seniales listarId(Integer id);

    Seniales getSenialesByRutaId(Integer rutaId);

    int addSeniales(Seniales s);

    int updSeniales(Seniales s);

    int delSeniales(Integer id);

    int delSenialesPorRutaId(Integer rutaId);
}
