package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.NadadorHistoricoRutas;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface NadadorHistoricoRutasInterface {
    int insertarRuta(NadadorHistoricoRutas r);
    List<NadadorHistoricoRutas> obtenerPorUsuarioYFecha(String usuarioId, LocalDate fecha);
    List<UUID> obtenerRecorridosPorFecha(String usuarioId, LocalDate fecha);
    List<NadadorHistoricoRutas> listar();
    List<NadadorHistoricoRutas> obtenerPorRecorridoId(UUID recorridoId);
}
