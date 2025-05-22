package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.Respaldo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RespaldoInterface extends JpaRepository<Respaldo, Long> {
    List<Respaldo> findByUsuarioId(String usuarioId); // ← este nombre será respetado en el Service
}
