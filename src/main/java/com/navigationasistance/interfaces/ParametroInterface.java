package com.navigationasistance.interfaces;

import java.util.List;

import com.navigationasistance.modelo.Parametro;

public interface ParametroInterface{
    List<Parametro> listar();

    Parametro listarClave(String clave);

    int addParametro(Parametro p);

    int updParametro(Parametro p);

    int delParametro(String clave);

}
