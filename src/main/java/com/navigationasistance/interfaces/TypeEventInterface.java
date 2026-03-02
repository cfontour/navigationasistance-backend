package com.navigationasistance.interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.navigationasistance.modelo.TypeEvent;

public interface TypeEventInterface {
    List<TypeEvent> listar();

    TypeEvent listarId(BigDecimal id);

    int addTypeEvent(TypeEvent t);

    int updTypeEvent(TypeEvent t);

    int delTypeEvent(BigDecimal id);
}
