package com.navigationasistance.mapper;

import com.navigationasistance.modelo.Persona;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonaRowMapper implements RowMapper<Persona> {
    @Override
    public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
        Persona persona = new Persona();
        persona.setId(rs.getString("id"));
        persona.setNombre(rs.getString("nombre"));
        persona.setApellido(rs.getString("apellido"));

        return persona;
    }
}
