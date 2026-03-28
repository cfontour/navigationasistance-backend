package com.navigationasistance.service;

import com.navigationasistance.interfaces.LorawanUplinkRawInterface;
import com.navigationasistance.modelo.LorawanUplinkRaw;
import com.navigationasistance.modeloDAO.LorawanUplinkRawDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LorawanUplinkRawService implements LorawanUplinkRawInterface {

    @Autowired
    LorawanUplinkRawDAO dao;

    @Override
    public List<LorawanUplinkRaw> listar() {
        return dao.listar();
    }

    @Override
    public LorawanUplinkRaw listarClave(String devEui) {
        return dao.listarClave(devEui);
    }

    @Override
    public int add(LorawanUplinkRaw obj) {
        return dao.add(obj);
    }

    @Override
    public int upd(LorawanUplinkRaw obj) {
        return dao.upd(obj);
    }

    @Override
    public int del(String devEui) {
        return dao.del(devEui);
    }
}
