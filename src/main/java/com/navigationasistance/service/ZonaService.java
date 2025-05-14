package com.navigationasistance.service;

import com.navigationasistance.interfaces.ZonaInterface;
import com.navigationasistance.modelo.Zona;
import com.navigationasistance.modeloDAO.ZonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonaService implements ZonaInterface{

    @Autowired
    ZonaDAO dao;

    @Override
    public List<Zona> listar() {
        return dao.listar();
    }

    @Override
    public Zona listarZona(String zona) {
        return dao.listarZona(zona);
    }

    @Override
    public Zona getZonaByZona(String zona) {
        return dao.getZonaByZona(zona);}

    @Override
    public int addZona(Zona z) {
        return dao.addZona(z);
    }

    @Override
    public int updZona(Zona z) {
        return dao.updZona(z);
    }

    @Override
    public int delZona(String zona) { return dao.delZona(zona); }

}
