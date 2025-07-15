package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.Rutasa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RutasaInterface extends JpaRepository<Rutasa, Integer> {
    // métodos personalizados válidos, si los hay
}
