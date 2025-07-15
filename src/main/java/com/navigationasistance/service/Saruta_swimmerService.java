package com.navigationasistance.service;

import com.navigationasistance.interfaces.Sarutas_swimmerInterface;
import com.navigationasistance.interfaces.RutasaInterface;
import com.navigationasistance.mapper.Saruta_swimmerMapper;
import com.navigationasistance.modelo.Saruta_swimmer;
import com.navigationasistance.modeloDAO.Saruta_swimmerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Saruta_swimmerService {

    @Autowired
    private Sarutas_swimmerInterface interfaceDAO;

    @Autowired
    private RutasaInterface rutasaInterface;  // ⬅️ NECESARIO para cargar la entidad gestionada

    @Autowired
    private Saruta_swimmerMapper mapper;

    public List<Saruta_swimmer> listar() {
        return interfaceDAO.findAll();
    }

    public Saruta_swimmer findById(Integer id) {
        return interfaceDAO.findById(id).orElse(null);
    }

    public Saruta_swimmer buscarPorUsuario(String usuarioId) {
        return interfaceDAO.findByUsuarioId(usuarioId).orElse(null);
    }

    public List<Saruta_swimmer> listarPorRuta(Integer rutaId) {
        return interfaceDAO.findByRutaId(rutaId);
    }

    public int addNadadorRuta(Saruta_swimmer nr) {
        if (nr.getRutaId() == null) {
            throw new RuntimeException("rutaId no puede ser nulo");
        }

        if (!rutasaInterface.existsById(nr.getRutaId())) {
            throw new RuntimeException("Ruta no encontrada");
        }

        return interfaceDAO.save(nr) != null ? 1 : 0;
    }

    public int delNadadorRuta(Integer id) {
        if (interfaceDAO.existsById(id)) {
            interfaceDAO.deleteById(id);
            return 1;
        }
        return 0;
    }

    public List<Saruta_swimmerDAO> getPorRuta(Integer rutaId) {
        return interfaceDAO.findByRutaId(rutaId)
                .stream()
                .map(mapper::toDAO)
                .collect(Collectors.toList());
    }

}
