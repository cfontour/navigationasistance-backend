package com.navigationasistance.service;

import com.navigationasistance.interfaces.GrupoInterface;
import com.navigationasistance.modelo.Grupo;
import com.navigationasistance.modeloDAO.GrupoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GrupoService implements GrupoInterface {

    @Autowired
    GrupoDAO dao;

    @Override
    public List<Grupo> listar() {return dao.listar();	}

    @Override
    public Grupo listarGrupo(String grupo) {
        return dao.listarGrupo(grupo);
    }

    @Override
    public int addGrupo(Grupo g) {
        return dao.addGrupo(g);
    }

    @Override
    public int updGrupo(Grupo g) {return dao.updGrupo(g);
    }

    @Override
    public int delGrupo(String grupo) {
        return dao.delGrupo(grupo);
    }

}
