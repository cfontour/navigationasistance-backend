package com.navigationasistance.modeloDAO;

import com.navigationasistance.modelo.Rutas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RutasDAO extends JpaRepository<Rutas, Integer> {
    // Métodos adicionales si los necesitás más adelante
}