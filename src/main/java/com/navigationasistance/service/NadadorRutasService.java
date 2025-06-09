package com.navigationasistance.service;

import com.navigationasistance.interfaces.NadadorRutasInterface;
import com.navigationasistance.modelo.NadadorRutas;
import com.navigationasistance.modeloDAO.NadadorRutasDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NadadorRutasService implements NadadorRutasInterface {

    @Autowired
    NadadorRutasDAO dao;

    @Override
    public List<NadadorRutas> listar() {
        return dao.findAll();
    }

    @Override
    public NadadorRutas listarId(Integer id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public NadadorRutas buscarPorUsuario(String usuarioId) {
        return dao.findByUsuarioId(usuarioId).orElse(null);
    }

    @Override
    public List<NadadorRutas> listarPorRuta(Integer rutaId) {
        return dao.findByRutaId(rutaId);
    }

    @Override
    public int add(NadadorRutas nr) {
        dao.save(nr);
        return 1;
    }

    @Override
    public int del(Integer id) {
        dao.deleteById(id);
        return 1;
    }
}
