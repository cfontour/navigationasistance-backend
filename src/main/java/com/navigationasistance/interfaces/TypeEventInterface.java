package com.navigationasistance.interfaces;

import java.util.List;

import com.navigationasistance.modelo.TypeEvent;

public interface TypeEventInterface {
    List<TypeEvent> listar();

    TypeEvent listarId(Integer id);

    int addTypeEvent(TypeEvent t);

    int updTypeEvent(TypeEvent t);

    int delTypeEvent(Integer id);
}
