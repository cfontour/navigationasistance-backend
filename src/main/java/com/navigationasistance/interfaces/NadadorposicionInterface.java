package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.NadadorPosicion;
import java.util.List;

public interface NadadorposicionInterface {
    List<NadadorPosicion> listar();
    NadadorPosicion getNadadorPosicion(String id);
    // ✅ NUEVO MÉTODO
    List<NadadorPosicion> listarVinculadosANadadorRutas();
    int upsertNadador(NadadorPosicion n);
    int updNadador(NadadorPosicion n);
    int updateEmergency(NadadorPosicion n);
    int delNadador(String id);
}