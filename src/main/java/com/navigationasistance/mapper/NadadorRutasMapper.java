package com.navigationasistance.mapper;

import com.navigationasistance.modelo.NadadorRutas;
import com.navigationasistance.modeloDAO.NadadorRutasDAO;
import org.springframework.stereotype.Component;

@Component
public class NadadorRutasMapper {

    public NadadorRutasDAO toDAO(NadadorRutas nr) {
        NadadorRutasDAO dao = new NadadorRutasDAO();
        dao.setId(nr.getId());
        dao.setUsuarioId(nr.getUsuarioId());
        dao.setRutaId(nr.getRutaId());  // Usamos getRutaId() y no getRuta()
        dao.setGrupoid(nr.getGrupoid());
        return dao;
    }

    public NadadorRutas toModel(NadadorRutasDAO dao) {
        NadadorRutas nr = new NadadorRutas();
        nr.setId(dao.getId());
        nr.setUsuarioId(dao.getUsuarioId());
        nr.setRutaId(dao.getRutaId());  // Usamos setRutaId(...) y no setRuta(...)
        nr.setGrupoid(dao.getGrupoid());
        return nr;
    }
}
