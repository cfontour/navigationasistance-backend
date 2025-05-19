package com.navigationasistance.mapper;

import com.navigationasistance.modelo.Respaldo;
import com.navigationasistance.modeloDAO.RespaldoDAO;
import org.springframework.stereotype.Component;

@Component
public class RespaldoMapper {

    public RespaldoDAO toDAO(Respaldo r) {
        RespaldoDAO dao = new RespaldoDAO();
        dao.setId(r.getId());
        dao.setContacto(r.getContacto());
        dao.setUsuarioId(r.getUsuarioId());
        return dao;
    }

    public Respaldo toModel(RespaldoDAO dao) {
        Respaldo r = new Respaldo();
        r.setId(dao.getId());
        r.setContacto(dao.getContacto());
        r.setUsuarioId(dao.getUsuarioId());
        return r;
    }
}
