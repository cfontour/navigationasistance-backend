package com.navigationasistance.service;

import com.navigationasistance.interfaces.RutasPuntosInterface;
import com.navigationasistance.modelo.RutasPuntos;
import com.navigationasistance.modeloDAO.RutasPuntosDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RutasPuntosService implements RutasPuntosInterface {

    @Autowired
    RutasPuntosDAO dao;

    @Override
    public List<RutasPuntos> listar() {
        return dao.findAll();
    }

    @Override
    public RutasPuntos listarId(Integer id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public List<RutasPuntos> listarPorRuta(Integer rutaId) {
        return dao.findByRutaIdOrderBySecuenciaAsc(rutaId);
    }

    @Override
    public int add(RutasPuntos p) {
        dao.save(p);
        return 1;
    }

    @Override
    public int upd(RutasPuntos p) {
        dao.save(p);
        return 1;
    }

    @Override
    public int del(Integer id) {
        dao.deleteById(id);
        return 1;
    }
}
