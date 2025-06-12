package com.navigationasistance.mapper;

import com.navigationasistance.modelo.Rutas;
import com.navigationasistance.modeloDAO.RutasDAO;
import org.springframework.stereotype.Component;

@Component
public class RutasMapper {

    public RutasDAO toDAO(Rutas r) {
        RutasDAO dao = new RutasDAO();
        dao.setId(r.getId());
        dao.setColor(r.getColor());
        dao.setNombre(r.getNombre());
        return dao;
    }

    public Rutas toModel(RutasDAO dao) {
        Rutas r = new Rutas();
        r.setId(dao.getId());
        r.setColor(dao.getColor());
        r.setNombre(dao.getNombre());
        return r;
    }
}
