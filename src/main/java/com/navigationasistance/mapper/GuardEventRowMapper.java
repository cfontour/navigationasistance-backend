package com.navigationasistance.mapper;

import com.navigationasistance.modelo.GuardEvent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class GuardEventRowMapper implements RowMapper<GuardEvent> {

    @Override
    public GuardEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
        GuardEvent guardEvent = new GuardEvent();

        guardEvent.setId((Integer) rs.getObject("id"));
        guardEvent.setUsuario_id(rs.getString("usuario_id"));
        guardEvent.setLocalidad_id(rs.getBigDecimal("localidad_id"));
        guardEvent.setGuard_lat(rs.getString("guard_lat"));
        guardEvent.setGuard_lng(rs.getString("guard_lng"));
        guardEvent.setType_id(rs.getBigDecimal("type_id"));
        guardEvent.setEvent_descripcion(rs.getString("event_descripcion"));
        guardEvent.setEvent_image(rs.getBytes("event_image"));

        // ✔ Conversión directa JDBC 4.2+ a LocalDateTime (sin Timestamp)
        guardEvent.setEvent_datetime(rs.getObject("event_datetime", LocalDateTime.class));

        return guardEvent;
    }
}
