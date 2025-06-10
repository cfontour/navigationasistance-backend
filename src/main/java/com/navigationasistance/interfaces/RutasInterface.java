package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.Rutas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RutasInterface extends JpaRepository<Rutas, Integer> {
    // métodos personalizados válidos, si los hay
}
