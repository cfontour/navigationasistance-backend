package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.UsuariocaPuntosControl;

import java.util.List;

public interface UsuariocaPuntosControlInterface {

    int save(UsuariocaPuntosControl u);

    List<UsuariocaPuntosControl> listar();

    List<UsuariocaPuntosControl> listarGrupo(String idGrupo);

    List<UsuariocaPuntosControl> listarPorNadadorrutaId(String nadadorrutaId);

    List<UsuariocaPuntosControl> listarPorRutaIdnadadorRutaId(Integer rutaId,String nadadorrutaId);

    List<UsuariocaPuntosControl> listarPorRutaId(Integer rutaId);

    int deleteUsuarioRutasPuntos(String nadadorruta_Id);
}
