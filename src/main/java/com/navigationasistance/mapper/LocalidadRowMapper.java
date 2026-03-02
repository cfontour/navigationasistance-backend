package com.navigationasistance.mapper;

import com.navigationasistance.modelo.Localidad;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocalidadRowMapper implements RowMapper<Localidad> {
    @Override
    public Localidad mapRow(ResultSet rs, int rowNum) throws SQLException {
        Localidad localidad = new Localidad();
        localidad.setId(rs.getBigDecimal("id"));
        localidad.setLocalidad_pais(rs.getString("localidad_pais"));
        localidad.setLocalidad_nombre(rs.getString("localidad_nombre"));

        return localidad;
    }
}
