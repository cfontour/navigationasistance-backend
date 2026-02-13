package com.navigationasistance.service;

import com.navigationasistance.interfaces.LocalidadInterface;
import com.navigationasistance.modelo.Localidad;
import com.navigationasistance.modeloDAO.LocalidadDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LocalidadService implements LocalidadInterface {

    @Autowired
    LocalidadDAO dao;

    @Override
    public List<Localidad> listar() {return dao.listar();	}

    @Override
    public Localidad listarId(Integer id) {
        return dao.listarId(id);
    }

    @Override
    public int addLocalidad(Localidad l) {
        return dao.addLocalidad(l);
    }

    @Override
    public int updLocalidad(Localidad l) {return dao.updLocalidad(l);
    }

    @Override
    public int delLocalidad(Integer id) {
        return dao.delLocalidad(id);
    }

}
