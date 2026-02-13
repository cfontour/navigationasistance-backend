package com.navigationasistance.service;

import com.navigationasistance.interfaces.GuardEventInterface;
import com.navigationasistance.modelo.GuardEvent;
import com.navigationasistance.modeloDAO.GuardEventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GuardEventService implements GuardEventInterface{

    @Autowired
    GuardEventDAO dao;

    @Override
    public List<GuardEvent> listar() {return dao.listar();	}

    @Override
    public GuardEvent listarId(Integer id) {
        return dao.listarId(id);
    }

    @Override
    public int addGuardEvent(GuardEvent g) {
        return dao.addGuardEvent(g);
    }

    @Override
    public int updGuardEvent(GuardEvent g) {return dao.updGuardEvent(g);
    }

    @Override
    public int delGuardEvent(Integer id) {
        return dao.delGuardEvent(id);
    }

}
