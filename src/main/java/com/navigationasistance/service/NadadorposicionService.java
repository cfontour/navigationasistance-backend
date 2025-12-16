package com.navigationasistance.service;

import com.navigationasistance.interfaces.NadadorposicionInterface;
import com.navigationasistance.modelo.NadadorPosicion;
import com.navigationasistance.modeloDAO.NadadorposicionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NadadorposicionService implements NadadorposicionInterface {

    @Autowired
    NadadorposicionDAO dao;

    @Override
    public List<NadadorPosicion> listar() {
        return dao.listar();
    }

    @Override
    public NadadorPosicion getNadadorPosicion(String id) { return dao.getNadadorPosicion(id); }

    @Override
    public List<NadadorPosicion> listarVinculadosANadadorRutas() {
        return dao.listarVinculadosANadadorRutas();
    }

    @Override
    public List<NadadorPosicion> listarVinculadosAGrupo(String grupoId) { return dao.listarVinculadosAGrupo(grupoId); }

    public int upsertNadador(NadadorPosicion n) {
        return dao.upsertNadador(n); // llama al método en el DAO (interface + implementación)
    }


    @Override
    public int updNadador(NadadorPosicion n) {
        return dao.updNadador(n);
    }

    // ✅ Cumple con la interfaz
    @Override
    public int updateEmergency(NadadorPosicion n) {
        return dao.updateEmergency(n);
    }

    // ✅ Método auxiliar para tu Controller
    public int updEmergency(String id, boolean emergency) {
        NadadorPosicion n = new NadadorPosicion();
        n.setUsuarioid(id);
        n.setEmergency(emergency);
        return updateEmergency(n);
    }

    @Override
    public int delNadador(String id) {
        return dao.delNadador(id);
    }

    @Override
    public Double calcularVelocidad(String usuarioId, UUID recorridoId) {
        return dao.calcularVelocidad(usuarioId, recorridoId);
    }

    @Override
    public List<NadadorPosicion> nadadoresCercanos(String usuarioId, String latitud, String longitud, Double distanciaMetros) {
        return dao.nadadoresCercanos(usuarioId, latitud, longitud, distanciaMetros);
    }

}