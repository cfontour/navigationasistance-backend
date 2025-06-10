package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.RutasPuntos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RutasPuntosInterface extends JpaRepository<RutasPuntos, Integer> {
    List<RutasPuntos> findByRutaIdOrderBySecuenciaAsc(Integer rutaId);
}
