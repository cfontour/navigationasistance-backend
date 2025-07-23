package com.navigationasistance.service;

import com.navigationasistance.modeloDAO.RutaSimpleDTO;
import com.navigationasistance.interfaces.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RutaSimpleService {
    @Autowired
    private RutaRepository rutaRepository;

    // ... otros métodos de tu servicio ...

    public List<RutaSimpleDTO> listarTodasRutasSimples() {
        return rutaRepository.findAllSimple();
    }
}
