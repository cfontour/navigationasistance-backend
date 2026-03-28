package com.navigationasistance.mapper;

import com.navigationasistance.modelo.MeasurementCatalog;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MeasurementCatalogRowMapper implements RowMapper<MeasurementCatalog> {

    @Override
    public MeasurementCatalog mapRow(ResultSet rs, int rowNum) throws SQLException {
        MeasurementCatalog obj = new MeasurementCatalog();

        obj.setMeasurementId(rs.getInt("measurement_id"));
        obj.setMeasurementName(rs.getString("measurement_name"));
        obj.setUnit(rs.getString("unit"));
        obj.setDescription(rs.getString("description"));
        obj.setActive(rs.getBoolean("active"));

        return obj;
    }
}