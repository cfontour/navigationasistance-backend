package com.navigationasistance.mapper;

import com.navigationasistance.modelo.MeasurementCatalog;
import com.navigationasistance.modelo.SensorMeasurement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class SensorMeasurementRowMapper implements RowMapper<SensorMeasurement> {

    @Override
    public SensorMeasurement mapRow(ResultSet rs, int rowNum) throws SQLException {
        SensorMeasurement obj = new SensorMeasurement();

        obj.setReceivedAt(toLocalDateTime(rs.getTimestamp("received_at")));
        obj.setDeviceId(rs.getString("device_id"));
        obj.setDevEui(rs.getString("dev_eui"));
        obj.setJoinEui(rs.getString("join_eui"));
        obj.setChannel(rs.getString("channel"));
        obj.setMeasurementName(rs.getString("measurement_name"));
        obj.setValueNumeric(rs.getBigDecimal("value_numeric"));
        obj.setValueText(rs.getString("value_text"));
        obj.setUnit(rs.getString("unit"));
        obj.setDeltaNumeric(rs.getBigDecimal("delta_numeric"));
        obj.setBattery(rs.getBigDecimal("battery"));
        obj.setRssi(rs.getBigDecimal("rssi"));
        obj.setSnr(rs.getBigDecimal("snr"));
        obj.setGatewayId(rs.getString("gateway_id"));
        obj.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

        int measurementId = rs.getInt("measurement_id");
        if (!rs.wasNull()) {
            MeasurementCatalog catalog = new MeasurementCatalog();
            catalog.setMeasurementId(measurementId);
            obj.setMeasurementCatalog(catalog);
        }

        return obj;
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }
}