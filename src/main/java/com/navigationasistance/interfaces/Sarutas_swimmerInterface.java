package com.navigationasistance.interfaces;


import com.navigationasistance.modelo.Saruta_swimmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Sarutas_swimmerInterface extends JpaRepository<Saruta_swimmer, Integer> {

    Optional<Saruta_swimmer> findByUsuarioId(String usuarioId);

    List<Saruta_swimmer> findByRutaId(Integer rutaId);
}
