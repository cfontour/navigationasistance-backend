package com.navigationasistance.service;

import com.navigationasistance.interfaces.UsuariocaPuntosControlInterface;
import com.navigationasistance.modelo.UsuariocaPuntosControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariocaPuntosControlService {

    @Autowired
    private UsuariocaPuntosControlInterface usuariocaPuntosControlDAO;

    public int save(UsuariocaPuntosControl u) {
        try {
            return usuariocaPuntosControlDAO.save(u);
        } catch (DuplicateKeyException e) {
            System.out.println("⚠️ Ya existe un registro con ese nadador y punto. Evitando duplicado.");
            return 0;
        } catch (Exception e) {
            System.err.println("🛑 Error al insertar punto de control: " + e.getMessage());
            return -1;
        }
    }

    public List<UsuariocaPuntosControl> listar() {
        return usuariocaPuntosControlDAO.listar();
    }

    public List<UsuariocaPuntosControl> listarPorNadadorrutaId(Integer nadadorrutaId) {
        return usuariocaPuntosControlDAO.listarPorNadadorrutaId(nadadorrutaId);
    }
}
