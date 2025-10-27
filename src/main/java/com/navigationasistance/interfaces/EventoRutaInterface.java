package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.EventoRuta;

import java.util.List;

public interface EventoRutaInterface {
    int agregar(EventoRuta eventoRuta);
    List<EventoRuta> listar();
    List<EventoRuta> listarPorUsuario(String usuarioId);
    List<EventoRuta> listarPorRuta(int rutaId);
    int actualizar(EventoRuta eventoRuta);
    int eliminar(int id);
    EventoRuta buscarPorId(int id);
}
