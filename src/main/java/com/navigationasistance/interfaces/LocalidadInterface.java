package com.navigationasistance.interfaces;

import java.util.List;

import com.navigationasistance.modelo.Localidad;

public interface LocalidadInterface {
    List<Localidad> listar();

    Localidad listarId(Integer id);

    int addLocalidad(Localidad l);

    int updLocalidad(Localidad l);

    int delLocalidad(Integer id);
}
