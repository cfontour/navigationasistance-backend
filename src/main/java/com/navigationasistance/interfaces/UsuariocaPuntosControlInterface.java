package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.UsuariocaPuntosControl;

import java.util.List;

public interface UsuariocaPuntosControlInterface {

    int save(UsuariocaPuntosControl u);

    List<UsuariocaPuntosControl> listar();

    List<UsuariocaPuntosControl> listarPorNadadorrutaId(Integer nadadorrutaId);

    List<UsuariocaPuntosControl> listarPorRutaIdnadadorRutaId(Integer nadadorrutaId);
}
