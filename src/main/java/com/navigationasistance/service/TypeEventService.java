package com.navigationasistance.service;

import com.navigationasistance.interfaces.TypeEventInterface;
import com.navigationasistance.modelo.TypeEvent;
import com.navigationasistance.modeloDAO.TypeEventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class TypeEventService implements TypeEventInterface {

    @Autowired
    TypeEventDAO dao;

    @Override
    public List<TypeEvent> listar() {return dao.listar();	}

    @Override
    public TypeEvent listarId(BigDecimal id) {
        return dao.listarId(id);
    }

    @Override
    public int addTypeEvent(TypeEvent t) {
        return dao.addTypeEvent(t);
    }

    @Override
    public int updTypeEvent(TypeEvent t) {return dao.updTypeEvent(t);
    }

    @Override
    public int delTypeEvent(BigDecimal id) {
        return dao.delTypeEvent(id);
    }

}
