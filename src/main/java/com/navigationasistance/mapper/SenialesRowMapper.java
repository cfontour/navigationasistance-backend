package com.navigationasistance.mapper;

import com.navigationasistance.modelo.Seniales;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SenialesRowMapper implements RowMapper<Seniales>  {

    @Override
    public Seniales mapRow(ResultSet rs, int rowNum) throws SQLException {
        Seniales seniales = new Seniales();
        seniales.setId(rs.getInt("id"));
        seniales.setRuta_id(rs.getInt("ruta_id"));
        seniales.setMts(rs.getInt("mts"));
        seniales.setLatl(rs.getFloat("latl"));
        seniales.setLngl(rs.getFloat("lngl"));
        seniales.setLatr(rs.getFloat("latr"));
        seniales.setLngr(rs.getFloat("lngr"));
        seniales.setLatc(rs.getFloat("latc"));
        seniales.setLngc(rs.getFloat("lngc"));
        seniales.setTipo(rs.getString("tipo"));
        // Asegúrate de establecer todos los campos de Usuario aquí
        return seniales;
    }
}

