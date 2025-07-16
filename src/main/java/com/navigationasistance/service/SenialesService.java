package com.navigationasistance.service;

import com.navigationasistance.interfaces.SenialesInterface;
import com.navigationasistance.modelo.Seniales;
import com.navigationasistance.modeloDAO.SenialesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SenialesService implements SenialesInterface {

    @Autowired
    SenialesDAO dao;

    @Override
    public List<Seniales> listar() {
        return dao.listar();
    }

    @Override
    public Seniales listarId(Integer id) {
        return dao.listarId(id);
    }

    @Override
    public Seniales getSenialesByRutaId(Integer rutaId) {
        return dao.getSenialesByRutaId(rutaId);
    }

    @Override
    public int addSeniales(Seniales s) {
        return dao.addSeniales(s);
    }

    @Override
    public int updSeniales(Seniales s) {
        return dao.updSeniales(s);
    }

    @Override
    public int delSeniales(Integer id) {
        return dao.delSeniales(id);
    }

    @Override
    public int delSenialesPorRutaId(Integer rutaId) {
        return dao.delSenialesPorRutaId(rutaId);
    }
}

