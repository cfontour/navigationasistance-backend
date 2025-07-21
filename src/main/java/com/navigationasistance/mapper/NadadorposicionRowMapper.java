package com.navigationasistance.mapper;

import com.navigationasistance.modelo.NadadorPosicion;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class NadadorposicionRowMapper implements RowMapper<NadadorPosicion> {
    @Override
    public NadadorPosicion mapRow(ResultSet rs, int rowNum) throws SQLException {
        NadadorPosicion nadadorPosicion = new NadadorPosicion();
        nadadorPosicion.setUsuarioid(rs.getString("usuario_id"));
        nadadorPosicion.setNadadorlat(rs.getString("nadadorlat"));
        nadadorPosicion.setNadadorlng(rs.getString("nadadorlng"));

        // Inicializa bearing a 0 si es nulo en la DB
        int valorBearing = rs.getInt("bearing");
        if (rs.wasNull()) {
            nadadorPosicion.setBearing(0); // Si el valor en DB es NULL, lo establece en 0
        } else {
            nadadorPosicion.setBearing(valorBearing); // Si no es nulo, establece el valor real
        }

        nadadorPosicion.setEmergency(rs.getBoolean("emergency"));

        Timestamp ts = rs.getTimestamp("fecha_ultima_actualizacion");
        if (ts != null) {
            nadadorPosicion.setFechaUltimaActualizacion(ts.toLocalDateTime());
        }

        boolean emergency = rs.getBoolean("emergency");
        nadadorPosicion.setEmergency(!rs.wasNull() ? emergency : null);

        return nadadorPosicion;
    }
}