package com.navigationasistance.service;

import com.navigationasistance.interfaces.RutasPuntosInterface;
import com.navigationasistance.mapper.RutasPuntosMapper;
import com.navigationasistance.modelo.RutasPuntos;
import com.navigationasistance.modeloDAO.RutasPuntosDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RutasPuntosService {

    @Autowired
    private RutasPuntosInterface rutasPuntosInterface;

    @Autowired
    private RutasPuntosMapper mapper;

    public List<RutasPuntos> listar() {
        return rutasPuntosInterface.findAll();
    }

    public List<RutasPuntosDAO> listarPorRuta(Integer rutaId) {
        return rutasPuntosInterface.findByRutaIdOrderBySecuenciaAsc(rutaId)
                .stream()
                .map(mapper::toDAO)
                .collect(Collectors.toList());
    }

    public int addRutasPuntos(RutasPuntos rp) {
        System.out.println(">>> DEBUG RP: " + rp);
        System.out.println(">>> RUTA: " + (rp.getRuta() != null ? rp.getRuta().getId() : "null"));
        return rutasPuntosInterface.save(rp) != null ? 1 : 0;
    }

    public int updRutasPuntos(RutasPuntos rp) {
        if (rutasPuntosInterface.existsById(rp.getId())) {
            rutasPuntosInterface.save(rp);
            return 1;
        }
        return 0;
    }

    public RutasPuntos findById(Integer id) {
        return rutasPuntosInterface.findById(id).orElse(null);
    }

    public int delRutasPuntos(Integer id) {
        if (rutasPuntosInterface.existsById(id)) {
            rutasPuntosInterface.deleteById(id);
            return 1;
        }
        return 0;
    }
}
