package com.navigationasistance.service;

import com.navigationasistance.interfaces.NadadorHistoricoRutasInterface;
import com.navigationasistance.modelo.NadadorHistoricoRutas;
import com.navigationasistance.modeloDAO.NadadorHistoricoRutasDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class NadadorhistoricoRutasService implements NadadorHistoricoRutasInterface {

    @Autowired
    NadadorHistoricoRutasDAO dao;

    @Override
    public List<NadadorHistoricoRutas> listar() {
        return dao.listar();
    }

    @Override
    public int insertarRuta(NadadorHistoricoRutas r) {
        return dao.insertarRuta(r);
    }

    @Override
    public List<NadadorHistoricoRutas> obtenerPorUsuarioYFecha(String usuarioId, LocalDate fecha) {
        return dao.obtenerPorUsuarioYFecha(usuarioId, fecha);
    }

    @Override
    public List<UUID> obtenerRecorridosPorFecha(String usuarioId, LocalDate fecha) {
        return dao.obtenerRecorridosPorFecha(usuarioId, fecha);
    }

    @Override
    public List<NadadorHistoricoRutas> obtenerPorRecorridoId(UUID recorridoId) {
        return dao.obtenerPorRecorridoId(recorridoId);
    }
}