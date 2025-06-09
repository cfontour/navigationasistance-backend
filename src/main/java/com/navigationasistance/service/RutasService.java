package com.navigationasistance.service;

import com.navigationasistance.interfaces.RutasInterface;
import com.navigationasistance.modelo.Rutas;
import com.navigationasistance.modeloDAO.RutasDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RutasService implements RutasInterface {

    @Autowired
    RutasDAO dao;

    @Override
    public List<Rutas> listar() {
        return dao.findAll();
    }

    @Override
    public Rutas listarId(Integer id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public int add(Rutas r) {
        dao.save(r);
        return 1;
    }

    @Override
    public int upd(Rutas r) {
        dao.save(r);
        return 1;
    }

    @Override
    public int del(Integer id) {
        dao.deleteById(id);
        return 1;
    }
}
