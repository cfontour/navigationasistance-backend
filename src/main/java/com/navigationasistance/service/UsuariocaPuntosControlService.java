package com.navigationasistance.service;

import com.navigationasistance.interfaces.UsuariocaPuntosControlInterface;
import com.navigationasistance.modelo.UsuariocaPuntosControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariocaPuntosControlService {

    @Autowired
    private UsuariocaPuntosControlInterface usuariocaPuntosControlDAO;

    public int save(UsuariocaPuntosControl u) {
        return usuariocaPuntosControlDAO.save(u);
    }

    public List<UsuariocaPuntosControl> listar() {
        return usuariocaPuntosControlDAO.listar();
    }

    public List<UsuariocaPuntosControl> listarPorNadadorrutaId(Integer nadadorrutaId) {
        return usuariocaPuntosControlDAO.listarPorNadadorrutaId(nadadorrutaId);
    }
}
