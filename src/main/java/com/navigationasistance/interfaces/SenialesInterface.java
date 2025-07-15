package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.Seniales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SenialesInterface extends JpaRepository<Seniales, Integer> {
    // métodos personalizados válidos, si los hay
}
