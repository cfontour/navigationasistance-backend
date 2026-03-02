package com.navigationasistance.interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.navigationasistance.modelo.Localidad;

public interface LocalidadInterface {
    List<Localidad> listar();

    Localidad listarId(BigDecimal id);

    int addLocalidad(Localidad l);

    int updLocalidad(Localidad l);

    int delLocalidad(BigDecimal id);
}
