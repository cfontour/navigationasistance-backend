package com.navigationasistance.service;

import com.navigationasistance.interfaces.NadadorRutasInterface;
import com.navigationasistance.interfaces.RutasInterface;
import com.navigationasistance.mapper.NadadorRutasMapper;
import com.navigationasistance.modelo.NadadorRutas;
import com.navigationasistance.modelo.Rutas;
import com.navigationasistance.modeloDAO.NadadorRutasDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NadadorRutasService {

    @Autowired
    private NadadorRutasInterface interfaceDAO;

    @Autowired
    private RutasInterface rutasInterface;  // ⬅️ NECESARIO para cargar la entidad gestionada

    @Autowired
    private NadadorRutasMapper mapper;

    public List<NadadorRutas> listar() {
        return interfaceDAO.findAll();
    }

    // ✅ NUEVO: listar por grupo
    public List<NadadorRutas> listarGrupo(String grupoid) {
        return interfaceDAO.findByGrupoid(grupoid);
    }

    public NadadorRutas findById(Integer id) {
        return interfaceDAO.findById(id).orElse(null);
    }

    public NadadorRutas buscarPorUsuario(String usuarioId) {
        return interfaceDAO.findByUsuarioId(usuarioId).orElse(null);
    }

    public List<NadadorRutas> listarPorRuta(Integer rutaId) {
        return interfaceDAO.findByRutaId(rutaId);
    }

    public int addNadadorRuta(NadadorRutas nr) {
        if (nr.getRutaId() == null) {
            throw new RuntimeException("rutaId no puede ser nulo");
        }

        if (!rutasInterface.existsById(nr.getRutaId())) {
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

    // 🔁 AHORA BORRA POR GRUPOID (no por id)
    public int delNadadorRutaGrupo(String grupoid) {
        // traigo todos los registros del grupo
        var lista = interfaceDAO.findByGrupoid(grupoid);
        if (lista == null || lista.isEmpty()) {
            return 0;
        }

        interfaceDAO.deleteAll(lista);
        return lista.size(); // cantidad de filas borradas
    }

    public List<NadadorRutasDAO> getPorRuta(Integer rutaId) {
        return interfaceDAO.findByRutaId(rutaId)
                .stream()
                .map(mapper::toDAO)
                .collect(Collectors.toList());
    }
}
