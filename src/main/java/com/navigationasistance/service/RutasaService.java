package com.navigationasistance.service;

import com.navigationasistance.interfaces.RutasaInterface;
import com.navigationasistance.mapper.RutasaMapper;
import com.navigationasistance.modelo.Rutasa;
import com.navigationasistance.modeloDAO.RutasaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class RutasaService {

    @Autowired
    private RutasaInterface rutasaInterface;

    @Autowired
    private RutasaMapper mapper;

    public List<Rutasa> listar() {
        return rutasaInterface.findAll();
    }

    public Rutasa listarId(Integer id) {
        return rutasaInterface.findById(id).orElse(null);
    }

    public int add(Rutasa r) {
        Rutasa guardada = rutasaInterface.save(r);
        return guardada.getId(); // retorna el ID autogenerado
    }

    public int upd(Rutasa r) {
        if (rutasaInterface.existsById(r.getId())) {
            rutasaInterface.save(r);
            return 1;
        }
        return 0;
    }

    public int del(Integer id) {
        if (rutasaInterface.existsById(id)) {
            rutasaInterface.deleteById(id);
            return 1;
        }
        return 0;
    }

    public List<RutasaDAO> listarDAO() {
        return rutasaInterface.findAll()
                .stream()
                .map(mapper::toDAO)
                .collect(Collectors.toList());
    }

}
