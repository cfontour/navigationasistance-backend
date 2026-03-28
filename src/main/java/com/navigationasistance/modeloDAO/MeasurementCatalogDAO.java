package com.navigationasistance.modeloDAO;

import com.navigationasistance.interfaces.MeasurementCatalogInterface;
import com.navigationasistance.mapper.MeasurementCatalogRowMapper;
import com.navigationasistance.modelo.MeasurementCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MeasurementCatalogDAO implements MeasurementCatalogInterface {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<MeasurementCatalog> listar() {
        String sql = "SELECT * FROM measurement_catalog";
        return template.query(sql, new MeasurementCatalogRowMapper());
    }

    @Override
    public MeasurementCatalog listarClave(String measurementName) {
        String sql = "SELECT * FROM measurement_catalog WHERE measurement_name = ?";
        return template.queryForObject(sql, new Object[]{measurementName}, new MeasurementCatalogRowMapper());
    }

    @Override
    public int add(MeasurementCatalog obj) {
        String sql = "INSERT INTO measurement_catalog(measurement_name, unit, description, active) VALUES (?,?,?,?)";

        return template.update(sql,
                obj.getMeasurementName(),
                obj.getUnit(),
                obj.getDescription(),
                obj.getActive()
        );
    }

    @Override
    public int upd(MeasurementCatalog obj) {
        String sql = "UPDATE measurement_catalog SET unit=?, description=?, active=? WHERE measurement_name=?";

        return template.update(sql,
                obj.getUnit(),
                obj.getDescription(),
                obj.getActive(),
                obj.getMeasurementName()
        );
    }

    @Override
    public int del(String measurementName) {
        String sql = "DELETE FROM measurement_catalog WHERE measurement_name=?";
        return template.update(sql, measurementName);
    }
}
