package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.Zona;

import java.util.List;

public interface ZonaInterface {

    List<Zona> listar();

    Zona listarZona(String zona);

    Zona getZonaByZona(String zona);

    int addZona(Zona z);

    int updZona(Zona z);
    int delZona(String zona);
}
