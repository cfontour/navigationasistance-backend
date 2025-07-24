package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.Rutas;
import com.navigationasistance.modeloDAO.RutaSimpleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

@Repository
public interface RutasInterface extends JpaRepository<Rutas, Integer> {
    // **NUEVA CONSULTA JPQL para obtener Rutas simples (sin puntos)**
    // ¡¡¡IMPORTANTE!!!: 'Rutasa' es el nombre de tu clase de entidad.
    // 'com.navigationasistance.modeloDAO.RutaSimpleDTO' es la ruta completa a tu DTO.
    @Query("SELECT new com.navigationasistance.modeloDAO.RutaSimpleDTO(r.id, r.nombre, r.color) FROM Rutasa r")
    static List<RutaSimpleDTO> findAllSimpleRoutes() // Renombré el método para claridad// métodos personalizados válidos, si los hay
    {
        return null;
    }
}
