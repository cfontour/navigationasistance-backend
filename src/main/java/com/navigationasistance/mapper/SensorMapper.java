package com.navigationasistance.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.navigationasistance.modelo.Sensor;

public class SensorMapper implements RowMapper<Sensor> {

    @Override
    public Sensor mapRow(ResultSet rs, int rowNum) throws SQLException {

        Sensor sensor = new Sensor();

        sensor.setSensorId(rs.getString("sensorId"));
        sensor.setSensorNombre(rs.getString("sensorNombre"));
        sensor.setSensorDescripcion(rs.getString("sensorDescripcion"));
        sensor.setSensorlat(rs.getString("sensorlat"));
        sensor.setSensorlng(rs.getString("sensorlng"));

        return sensor;
    }
}