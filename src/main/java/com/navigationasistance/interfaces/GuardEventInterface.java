package com.navigationasistance.interfaces;

import java.util.List;

import com.navigationasistance.modelo.GuardEvent;

public interface GuardEventInterface {
    List<GuardEvent> listar();

    GuardEvent listarId(Integer id);

    int addGuardEvent(GuardEvent g);

    int updGuardEvent(GuardEvent g);

    int delGuardEvent(Integer id);
}
