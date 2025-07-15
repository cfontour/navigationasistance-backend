package com.navigationasistance.mapper;

import com.navigationasistance.modelo.Rutasa;
import com.navigationasistance.modeloDAO.RutasaDAO;
import org.springframework.stereotype.Component;

@Component
public class RutasaMapper {

    public RutasaDAO toDAO(Rutasa r) {
        RutasaDAO dao = new RutasaDAO();
        dao.setId(r.getId());
        dao.setColor(r.getColor());
        dao.setNombre(r.getNombre());
        return dao;
    }

    public Rutasa toModel(RutasaDAO dao) {
        Rutasa r = new Rutasa();
        r.setId(dao.getId());
        r.setColor(dao.getColor());
        r.setNombre(dao.getNombre());
        return r;
    }

}
