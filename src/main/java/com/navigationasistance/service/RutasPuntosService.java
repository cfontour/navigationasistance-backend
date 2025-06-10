package com.navigationasistance.service;

import com.navigationasistance.interfaces.RutasInterface;
import com.navigationasistance.interfaces.RutasPuntosInterface;
import com.navigationasistance.mapper.RutasPuntosMapper;
import com.navigationasistance.modelo.Rutas;
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

    @Autowired
    private RutasInterface rutasInterface;

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
        if (rp.getRuta() != null && rp.getRuta().getId() != null) {
            Rutas ruta = rutasInterface.findById(rp.getRuta().getId())
                    .orElseThrow(() -> new RuntimeException("Ruta no encontrada con ID: " + rp.getRuta().getId()));
            rp.setRuta(ruta); // << aquí se asocia la instancia persistida
        } else {
            throw new RuntimeException("El campo ruta.id es obligatorio");
        }

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
