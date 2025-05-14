package com.navigationasistance.mapper;

import com.navigationasistance.modelo.NadadorHistoricoRutas;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

public class NadadorhistoricoRutasRowMapper implements RowMapper<NadadorHistoricoRutas> {
    @Override
    public NadadorHistoricoRutas mapRow(ResultSet rs, int rowNum) throws SQLException {
        NadadorHistoricoRutas ruta = new NadadorHistoricoRutas();

        ruta.setId(rs.getLong("id"));
        ruta.setUsuarioid(rs.getString("usuario_id"));
        ruta.setRecorridoid(UUID.fromString(rs.getString("recorrido_id")));
        ruta.setNadadorfecha(rs.getObject("nadadorfecha", LocalDate.class));
        ruta.setNadadorhora(rs.getTimestamp("nadadorhora"));
        ruta.setSecuencia(rs.getInt("secuencia"));
        ruta.setNadadorlat(rs.getString("nadadorlat"));
        ruta.setNadadorlng(rs.getString("nadadorlng"));

        return ruta;
    }
}
