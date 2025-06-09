package com.navigationasistance.modeloDAO;

import com.navigationasistance.modelo.RutasPuntos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RutasPuntosDAO extends JpaRepository<RutasPuntos, Integer> {
    List<RutasPuntos> findByRutaIdOrderBySecuenciaAsc(Integer rutaId);
}
