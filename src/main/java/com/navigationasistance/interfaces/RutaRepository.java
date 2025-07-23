package com.navigationasistance.interfaces;

import com.navigationasistance.modeloDAO.RutaSimpleDTO;
import com.navigationasistance.modelo.Rutas; // <-- ¡Esta es tu entidad!
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RutaRepository extends JpaRepository<Rutas, Integer> {

    // **CORRECCIÓN CLAVE:** Cambiado 'Ruta' a 'Rutas' en el FROM de la Query.
    @Query("SELECT new com.navigationasistance.modeloDAO.RutaSimpleDTO(r.id, r.nombre, r.color) FROM rutas r")
    List<RutaSimpleDTO> findAllSimple();
}