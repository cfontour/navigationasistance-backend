package com.navigationasistance.mapper;

import com.navigationasistance.modelo.Seniales;
import com.navigationasistance.modeloDAO.SenialesDAO;
import org.springframework.stereotype.Component;

@Component
public class SenialesMapper {

    public SenialesDAO toDAO(Seniales s) {
        SenialesDAO dao = new SenialesDAO();
        dao.setId(s.getId());
        return dao;
    }

    public Seniales toModel(SenialesDAO dao) {
        Seniales s = new Seniales();
        s.setId(dao.getId());
        return s;
    }

}
