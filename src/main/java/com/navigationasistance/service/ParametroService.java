package com.navigationasistance.service;

import com.navigationasistance.interfaces.ParametroInterface;
import com.navigationasistance.modelo.Parametro;
import com.navigationasistance.modeloDAO.ParametroDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ParametroService implements ParametroInterface {

    @Autowired
    ParametroDAO dao;

    @Override
    public List<Parametro> listar() {return dao.listar();	}

    @Override
    public Parametro listarClave(String clave) {
        return dao.listarClave(clave);
    }

    @Override
    public int addParametro(Parametro p) {
        return dao.addParametro(p);
    }

    @Override
    public int updParametro(Parametro p) {return dao.updParametro(p);
    }

    @Override
    public int delParametro(String clave) {
        return dao.delParametro(clave);
    }

}
