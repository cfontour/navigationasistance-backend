package com.navigationasistance.interfaces;

// Ubicación: com.example.geotraser.repository
import com.navigationasistance.modeloDAO.RutaSimpleDTO; // Importa tu DTO
import com.navigationasistance.modelo.Rutas; // Asumiendo tu entidad Ruta
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RutaRepository extends JpaRepository<Rutas, Integer> {

    // Método para obtener todas las rutas, proyectando a RutaSimpleDTO
    // Asegúrate de que los nombres de los parámetros del constructor RutaSimpleDTO
    // coincidan con los campos de tu entidad Ruta (id, nombre, color)
    @Query("SELECT new com.navigationasistance.modeloDAO.RutaSimpleDTO(r.id, r.nombre, r.color) FROM Ruta r")
    List<RutaSimpleDTO> findAllSimple();

    // Si tu entidad Ruta no tiene el campo 'color' directamente sino que viene de otro lado,
    // o si el 'color' que usas para ordenar en el frontend proviene de otra tabla,
    // deberías ajustar la consulta @Query según tu esquema de base de datos.
    // Por ejemplo, si 'color' viene de la tabla 'TipoRuta' relacionada:
    // @Query("SELECT new com.example.geotraser.dto.RutaSimpleDTO(r.id, r.nombre, tr.nombreColor) FROM Ruta r JOIN r.tipoRuta tr")
    // List<RutaSimpleDTO> findAllSimple();
}