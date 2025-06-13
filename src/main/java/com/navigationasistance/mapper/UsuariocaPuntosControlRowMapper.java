package com.navigationasistance.mapper;

import com.navigationasistance.modelo.UsuariocaPuntosControl;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class UsuariocaPuntosControlRowMapper implements RowMapper<UsuariocaPuntosControl> {

    @Override
    public UsuariocaPuntosControl mapRow(ResultSet rs, int rowNum) throws SQLException {
        UsuariocaPuntosControl u = new UsuariocaPuntosControl();
        u.setId(rs.getInt("id"));
        u.setNadadorrutaId(rs.getString("nadadorruta_id"));
        u.setPuntoControl(rs.getString("punto_control"));
        u.setFechaHora(rs.getObject("fecha_hora", LocalDateTime.class));
        return u;
    }
}
