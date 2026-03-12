package com.navigationasistance.interfaces;

import java.util.List;

import com.navigationasistance.modelo.Grupo;

public interface GrupoInterface{
    List<Grupo> listar();

    Grupo listarGrupo(String grupo);

    int addGrupo(Grupo p);

    int updGrupo(Grupo g);

    int delGrupo(String grupo);

}
