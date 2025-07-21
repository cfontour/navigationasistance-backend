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
        // ✅ CORRECCIÓN para BEARING: Usar getObject para mapear NULL de DB a null en Java
        // Si el valor en la DB es NULL, esto asignará 'null' al Integer de Java.
        nadadorPosicion.setBearing(rs.getObject("bearing", Integer.class));

        // ✅ CORRECCIÓN para EMERGENCY: Usar getObject para mapear NULL de DB a null en Java
        // Si el valor en la DB es NULL, esto asignará 'null' al Boolean de Java.
        nadadorPosicion.setEmergency(rs.getObject("emergency", Boolean.class));

        Timestamp ts = rs.getTimestamp("fecha_ultima_actualizacion");
        if (ts != null) {
            nadadorPosicion.setFechaUltimaActualizacion(ts.toLocalDateTime());
        }

        //boolean emergency = rs.getBoolean("emergency");
        //nadadorPosicion.setEmergency(!rs.wasNull() ? emergency : null);

        return nadadorPosicion;
    }
}