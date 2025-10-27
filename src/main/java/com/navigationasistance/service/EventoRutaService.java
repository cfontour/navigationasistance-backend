package com.navigationasistance.service;

import com.navigationasistance.interfaces.EventoRutaInterface;
import com.navigationasistance.modelo.EventoRuta;
import com.navigationasistance.modeloDAO.EventoRutaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoRutaService implements EventoRutaInterface {

    @Autowired
    private EventoRutaDAO eventoRutaDAO;

    @Override
    public int agregar(EventoRuta eventoRuta) {
        return eventoRutaDAO.agregar(eventoRuta);
    }

    @Override
    public List<EventoRuta> listar() {
        return eventoRutaDAO.listar();
    }

    @Override
    public List<EventoRuta> listarPorUsuario(String usuarioId) {
        return eventoRutaDAO.listarPorUsuario(usuarioId);
    }

    @Override
    public List<EventoRuta> listarPorRuta(int rutaId) {
        return eventoRutaDAO.listarPorRuta(rutaId);
    }

    @Override
    public int actualizar(EventoRuta eventoRuta) {
        return eventoRutaDAO.actualizar(eventoRuta);
    }

    @Override
    public int eliminar(int id) {
        return eventoRutaDAO.eliminar(id);
    }

    @Override
    public EventoRuta buscarPorId(int id) {
        return eventoRutaDAO.buscarPorId(id);
    }
}
