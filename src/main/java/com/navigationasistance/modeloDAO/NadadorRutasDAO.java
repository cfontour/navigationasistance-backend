package com.navigationasistance.modeloDAO;

import com.navigationasistance.modelo.NadadorRutas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface NadadorRutasDAO extends JpaRepository<NadadorRutas, Integer> {
    Optional<NadadorRutas> findByUsuarioId(String usuarioId);
    List<NadadorRutas> findByRutaId(Integer rutaId);
}
