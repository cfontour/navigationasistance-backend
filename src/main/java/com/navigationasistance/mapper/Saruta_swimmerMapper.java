package com.navigationasistance.mapper;

import com.navigationasistance.modelo.Saruta_swimmer;
import com.navigationasistance.modeloDAO.Saruta_swimmerDAO;
import org.springframework.stereotype.Component;

@Component
public class Saruta_swimmerMapper {

    public Saruta_swimmerDAO toDAO(Saruta_swimmer rs) {
        Saruta_swimmerDAO dao = new Saruta_swimmerDAO();
        dao.setId(rs.getId());
        dao.setUsuarioId(rs.getUsuarioId());
        dao.setRutaId(rs.getRutaId());  // Usamos getRutaId() y no getRuta()
        return dao;
    }

    public Saruta_swimmer toModel(Saruta_swimmerDAO dao) {
        Saruta_swimmer sr = new Saruta_swimmer();
        sr.setId(dao.getId());
        sr.setUsuarioId(dao.getUsuarioId());
        sr.setRutaId(dao.getRutaId());  // Usamos setRutaId(...) y no setRuta(...)
        return sr;
    }
}
