package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.NadadorRutas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NadadorRutasInterface extends JpaRepository<NadadorRutas, Integer> {

    Optional<NadadorRutas> findByUsuarioId(String usuarioId);

    List<NadadorRutas> findByRutaId(Integer rutaId);
}
