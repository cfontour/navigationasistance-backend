package com.navigationasistance.interfaces;

import java.util.List;

import com.navigationasistance.modelo.LorawanUplinkRaw;

public interface LorawanUplinkRawInterface {

    List<LorawanUplinkRaw> listar();

    LorawanUplinkRaw listarClave(String devEui);

    int add(LorawanUplinkRaw obj);

    int upd(LorawanUplinkRaw obj);

    int del(String devEui);
}
