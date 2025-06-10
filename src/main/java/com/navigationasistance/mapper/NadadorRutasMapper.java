package com.navigationasistance.mapper;

import com.navigationasistance.modelo.NadadorRutas;
import com.navigationasistance.modelo.Rutas;
import com.navigationasistance.modeloDAO.NadadorRutasDAO;
import org.springframework.stereotype.Component;

@Component
public class NadadorRutasMapper {

    public NadadorRutasDAO toDAO(NadadorRutas nr) {
        NadadorRutasDAO dao = new NadadorRutasDAO();
        dao.setId(nr.getId());
        dao.setUsuarioId(nr.getUsuarioId());
        dao.setRutaId(nr.getRuta().getId());
        return dao;
    }

    public NadadorRutas toModel(NadadorRutasDAO dao) {
        NadadorRutas nr = new NadadorRutas();
        nr.setId(dao.getId());
        nr.setUsuarioId(dao.getUsuarioId());

        Rutas ruta = new Rutas();
        ruta.setId(dao.getRutaId());
        nr.setRuta(ruta);

        return nr;
    }
}
