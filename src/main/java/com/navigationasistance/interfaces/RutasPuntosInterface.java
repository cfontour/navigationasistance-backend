package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.RutasPuntos;

import java.util.List;

public interface RutasPuntosInterface {
    List<RutasPuntos> listar();
    RutasPuntos listarId(Integer id);
    List<RutasPuntos> listarPorRuta(Integer rutaId);
    int add(RutasPuntos p);
    int upd(RutasPuntos p);
    int del(Integer id);
}
