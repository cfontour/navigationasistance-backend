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

        // Manejo de BEARING: Si es NULL en la DB, se establece a 0 en el objeto Java.
        // Si no es NULL, se usa el valor de la DB.
        int bearingDb = rs.getInt("bearing"); // Lee el valor como int
        if (rs.wasNull()) { // Verifica si el valor leído fue NULL en la DB
            nadadorPosicion.setBearing(0); // Establece 0 si fue NULL
        } else {
            nadadorPosicion.setBearing(bearingDb); // Usa el valor de la DB
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