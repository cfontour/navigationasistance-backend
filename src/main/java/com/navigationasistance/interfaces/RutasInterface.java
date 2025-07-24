package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.Rutas;
import com.navigationasistance.modeloDAO.RutaSimpleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
@Repository
public interface RutasInterface extends JpaRepository<Rutas, Integer> { // Confirmado: entidad Rutas, ID Integer

    // **NUEVA CONSULTA JPQL para obtener Rutas simples (sin puntos)**
    // ¡¡¡IMPORTANTE!!!: 'Rutas' es el nombre de tu clase de entidad.
    // 'com.navigationasistance.modeloDAO.RutaSimpleDTO' es la ruta completa a tu DTO.
    @Query("SELECT new com.navigationasistance.modeloDAO.RutaSimpleDTO(r.id, r.nombre, r.color) FROM Rutas r")
    List<RutaSimpleDTO> findAllSimpleRoutes(); // <<<<<< ¡¡¡ASÍ ES COMO DEBE QUEDAR!!! Sin 'static' y sin '{ return null; }'
}
