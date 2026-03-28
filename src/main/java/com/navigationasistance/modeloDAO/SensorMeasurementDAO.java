package com.navigationasistance.modeloDAO;

import com.navigationasistance.interfaces.SensorMeasurementInterface;
import com.navigationasistance.mapper.SensorMeasurementRowMapper;
import com.navigationasistance.modelo.SensorMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SensorMeasurementDAO implements SensorMeasurementInterface {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<SensorMeasurement> listar() {
        String sql = "SELECT * FROM sensor_measurement where device_id = 'geotraser-s2100-1'";
        return template.query(sql, new SensorMeasurementRowMapper());
    }

    @Override
    public SensorMeasurement listarClave(String devEui) {
        String sql = "SELECT * FROM sensor_measurement WHERE dev_eui = ? LIMIT 1";
        return template.queryForObject(sql, new Object[]{devEui}, new SensorMeasurementRowMapper());
    }

    @Override
    public int add(SensorMeasurement obj) {
        String sql = "INSERT INTO sensor_measurement(received_at, device_id, dev_eui, join_eui, channel, measurement_id, measurement_name, value_numeric, value_text, unit, battery, rssi, snr, gateway_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        return template.update(sql,
                obj.getReceivedAt(),
                obj.getDeviceId(),
                obj.getDevEui(),
                obj.getJoinEui(),
                obj.getChannel(),
                obj.getMeasurementCatalog() != null ? obj.getMeasurementCatalog().getMeasurementId() : null,
                obj.getMeasurementName(),
                obj.getValueNumeric(),
                obj.getValueText(),
                obj.getUnit(),
                obj.getBattery(),
                obj.getRssi(),
                obj.getSnr(),
                obj.getGatewayId()
        );
    }

    @Override
    public int upd(SensorMeasurement obj) {
        String sql = "UPDATE sensor_measurement SET received_at=?, device_id=?, join_eui=?, channel=?, measurement_id=?, measurement_name=?, value_numeric=?, value_text=?, unit=?, battery=?, rssi=?, snr=?, gateway_id=? WHERE dev_eui=?";

        return template.update(sql,
                obj.getReceivedAt(),
                obj.getDeviceId(),
                obj.getJoinEui(),
                obj.getChannel(),
                obj.getMeasurementCatalog() != null ? obj.getMeasurementCatalog().getMeasurementId() : null,
                obj.getMeasurementName(),
                obj.getValueNumeric(),
                obj.getValueText(),
                obj.getUnit(),
                obj.getBattery(),
                obj.getRssi(),
                obj.getSnr(),
                obj.getGatewayId(),
                obj.getDevEui()
        );
    }

    @Override
    public int del(String devEui) {
        String sql = "DELETE FROM sensor_measurement WHERE dev_eui=?";
        return template.update(sql, devEui);
    }
}