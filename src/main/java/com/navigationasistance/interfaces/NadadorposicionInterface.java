package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.NadadorPosicion;
import java.util.List;
import java.util.UUID;

public interface NadadorposicionInterface {
    List<NadadorPosicion> listar();
    NadadorPosicion getNadadorPosicion(String id);
    List<NadadorPosicion> listarVinculadosANadadorRutas();
    List<NadadorPosicion> listarVinculadosAGrupo(String grupoId);
    int upsertNadador(NadadorPosicion n);
    int updNadador(NadadorPosicion n);
    int updateEmergency(NadadorPosicion n);
    int delNadador(String id);
    Double calcularVelocidad(String usuarioId, UUID recorridoId);
    List<NadadorPosicion> nadadoresCercanos(String usuarioId, String latitud, String longitud, Double distanciaMetros);
}