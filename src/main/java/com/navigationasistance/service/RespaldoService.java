package com.navigationasistance.service;

import com.navigationasistance.interfaces.RespaldoInterface;
import com.navigationasistance.mapper.RespaldoMapper;
import com.navigationasistance.modelo.Respaldo;
import com.navigationasistance.modeloDAO.RespaldoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RespaldoService {

    @Autowired
    private RespaldoInterface respaldoInterface;

    @Autowired
    private RespaldoMapper mapper;

    public List<Respaldo> listar() {
        return respaldoInterface.findAll();
    }

    public List<Respaldo> listarPorUsuario(String usuarioId) {
        return respaldoInterface.findByUsuarioId(usuarioId);
    }

    public int addRespaldo(Respaldo respaldo) {
        return respaldoInterface.save(respaldo) != null ? 1 : 0;
    }

    public int updRespaldo(Respaldo respaldo) {
        if (respaldoInterface.existsById(respaldo.getId())) {
            respaldoInterface.save(respaldo);
            return 1;
        }
        return 0;
    }

    public int delRespaldo(Long id) {
        if (respaldoInterface.existsById(id)) {
            respaldoInterface.deleteById(id);
            return 1;
        }
        return 0;
    }

    public List<RespaldoDAO> getRespaldoPorUsuario(String usuarioId) {
        return respaldoInterface.findByUsuarioId(usuarioId)
                .stream()
                .map(mapper::toDAO)
                .collect(Collectors.toList());
    }
}
