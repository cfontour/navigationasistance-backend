package com.navigationasistance.service;

import com.navigationasistance.interfaces.SenialesInterface;
import com.navigationasistance.mapper.SenialesMapper;
import com.navigationasistance.modelo.Seniales;
import com.navigationasistance.modeloDAO.SenialesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SenialesService {

    @Autowired
    private SenialesInterface senialesInterface;

    @Autowired
    private SenialesMapper mapper;

    public List<Seniales> listar() {
        return senialesInterface.findAll();

    }

    public Seniales listarId(Integer id) {
        return senialesInterface.findById(id).orElse(null);
    }

    public int add(Seniales s) {
        Seniales guardada = senialesInterface.save(s);
        return guardada.getId(); // retorna el ID autogenerado
    }

    public int upd(Seniales s) {
        if (senialesInterface.existsById(s.getId())) {
            senialesInterface.save(s);
            return 1;
        }
        return 0;
    }

    public int del(Integer id) {
        if (senialesInterface.existsById(id)) {
            senialesInterface.deleteById(id);
            return 1;
        }
        return 0;
    }

    public List<SenialesDAO> listarDAO() {
        return senialesInterface.findAll()
                .stream()
                .map(mapper::toDAO)
                .collect(Collectors.toList());
    }
}

