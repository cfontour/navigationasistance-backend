package com.navigationasistance.mapper;

import com.navigationasistance.modelo.EventoRuta;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class EventoRutaRowMapper implements RowMapper<EventoRuta> {

    @Override
    public EventoRuta mapRow(ResultSet rs, int rowNum) throws SQLException {
        EventoRuta eventoRuta = new EventoRuta();
        eventoRuta.setId(rs.getInt("id"));
        eventoRuta.setRutaId(rs.getInt("ruta_id"));
        eventoRuta.setUsuarioId(rs.getString("usuario_id"));

        // senial_id puede ser null, así que verificamos
        int senialId = rs.getInt("senial_id");
        if (rs.wasNull()) {
            eventoRuta.setSenialId(null);
        } else {
            eventoRuta.setSenialId(senialId);
        }

        eventoRuta.setEvento(rs.getString("evento"));
        eventoRuta.setFecha(rs.getTimestamp("fecha"));

        return eventoRuta;
    }
}

