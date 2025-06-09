package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.NadadorRutas;

import java.util.List;

public interface NadadorRutasInterface {
    List<NadadorRutas> listar();
    NadadorRutas listarId(Integer id);
    NadadorRutas buscarPorUsuario(String usuarioId);
    List<NadadorRutas> listarPorRuta(Integer rutaId);
    int add(NadadorRutas nr);
    int del(Integer id);
}
