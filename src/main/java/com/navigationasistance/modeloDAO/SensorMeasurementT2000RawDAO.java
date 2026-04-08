package com.navigationasistance.modeloDAO;

import com.navigationasistance.interfaces.SensorMeasurementT2000RawInterface;
import com.navigationasistance.mapper.SensorMeasurementT2000RawRowMapper;
import com.navigationasistance.modelo.SensorMeasurementT2000Raw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SensorMeasurementT2000RawDAO implements SensorMeasurementT2000RawInterface {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<SensorMeasurementT2000Raw> listar() {
        String sql = "SELECT * FROM sensor_measurement_t2000_raw";
        return template.query(sql, new SensorMeasurementT2000RawRowMapper());
    }

    @Override
    public SensorMeasurementT2000Raw listarClave(String devEui) {
        String sql = "SELECT * FROM sensor_measurement_t2000_raw WHERE dev_eui = ? ORDER BY createdAt DESC LIMIT 1";
        return template.queryForObject(sql, new Object[]{devEui}, new SensorMeasurementT2000RawRowMapper());
    }

    @Override
    public int add(SensorMeasurementT2000Raw s) {
        String sql = "INSERT INTO sensor_measurement_t2000_raw(" +
                "device_id, dev_eui, join_eui, dev_addr, received_at, f_port, f_cnt, " +
                "rssi, snr, frequency, gateway_id, payload, decoded_payload, frm_payload" +
                ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        return template.update(sql,
                s.getDeviceId(),
                s.getDevEui(),
                s.getJoinEui(),
                s.getDevAddr(),
                s.getReceivedAt(),
                s.getFPort(),
                s.getFCnt(),
                s.getRssi(),
                s.getSnr(),
                s.getFrequency(),
                s.getGatewayId(),
                s.getPayload(),
                s.getDecodedPayload(),
                s.getFrmPayload()
        );
    }

    @Override
    public int delAll() {
        String sql = "DELETE FROM sensor_measurement_t2000_raw";
        return template.update(sql);
    }
}