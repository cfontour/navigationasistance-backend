package com.navigationasistance.service;

import com.navigationasistance.interfaces.RutasInterface;
import com.navigationasistance.mapper.RutasMapper;
import com.navigationasistance.modelo.Rutas;
import com.navigationasistance.modeloDAO.RutasDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RutasService {

    @Autowired
    private RutasInterface rutasInterface;

    @Autowired
    private RutasMapper mapper;

    public List<Rutas> listar() {
        return rutasInterface.findAll();
    }

    public Rutas listarId(Integer id) {
        return rutasInterface.findById(id).orElse(null);
    }

    public int add(Rutas r) {
        return rutasInterface.save(r) != null ? 1 : 0;
    }

    public int upd(Rutas r) {
        if (rutasInterface.existsById(r.getId())) {
            rutasInterface.save(r);
            return 1;
        }
        return 0;
    }

    public int del(Integer id) {
        if (rutasInterface.existsById(id)) {
            rutasInterface.deleteById(id);
            return 1;
        }
        return 0;
    }

    public List<RutasDAO> listarDAO() {
        return rutasInterface.findAll()
                .stream()
                .map(mapper::toDAO)
                .collect(Collectors.toList());
    }
}
