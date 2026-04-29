package com.navigationasistance.modeloDAO;

import com.navigationasistance.interfaces.SensorInterface;
import com.navigationasistance.mapper.SensorMapper;
import com.navigationasistance.modelo.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SensorDAO implements SensorInterface {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<Sensor> listar() {
        String sql = "SELECT * FROM sensor";
        return template.query(sql, new SensorMapper());
    }

    @Override
    public Sensor listarId(String sensorId) {
        String sql = "SELECT * FROM sensor WHERE sensorId = ?";
        return template.queryForObject(sql, new Object[]{sensorId}, new SensorMapper());
    }

    @Override
    public int addSensor(Sensor s) {
        String sql = "INSERT INTO sensor(sensorId, sensorNombre, sensorDescripcion, sensorlat, sensorlng) VALUES(?,?,?,?,?)";
        return template.update(sql,
                s.getSensorId(),
                s.getSensorNombre(),
                s.getSensorDescripcion(),
                s.getSensorlat(),
                s.getSensorlng()
        );
    }

    @Override
    public int updSensor(Sensor s) {
        String sql = "UPDATE sensor SET sensorNombre=?, sensorDescripcion=?, sensorlat=?, sensorlng=? WHERE sensorId=?";
        return template.update(sql,
                s.getSensorNombre(),
                s.getSensorDescripcion(),
                s.getSensorlat(),
                s.getSensorlng(),
                s.getSensorId()
        );
    }

    @Override
    public int delSensor(String sensorId) {
        String sql = "DELETE FROM sensor WHERE sensorId=?";
        return template.update(sql, sensorId);
    }
}