package com.navigationasistance.mapper;

import com.navigationasistance.modelo.SensorMeasurementT2000Raw;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SensorMeasurementT2000RawRowMapper implements RowMapper<SensorMeasurementT2000Raw> {

    @Override
    public SensorMeasurementT2000Raw mapRow(ResultSet rs, int rowNum) throws SQLException {

        SensorMeasurementT2000Raw s = new SensorMeasurementT2000Raw();

        s.setId(rs.getLong("id"));
        s.setDeviceId(rs.getString("device_id"));
        s.setDevEui(rs.getString("dev_eui"));
        s.setJoinEui(rs.getString("join_eui"));
        s.setDevAddr(rs.getString("dev_addr"));

        s.setReceivedAt(rs.getTimestamp("received_at"));
        s.setFPort(rs.getInt("f_port"));
        s.setFCnt(rs.getInt("f_cnt"));

        s.setRssi(rs.getDouble("rssi"));
        s.setSnr(rs.getDouble("snr"));
        s.setFrequency(rs.getLong("frequency"));

        s.setGatewayId(rs.getString("gateway_id"));

        s.setPayload(rs.getString("payload"));
        s.setDecodedPayload(rs.getString("decoded_payload"));
        s.setFrmPayload(rs.getString("frm_payload"));

        s.setCreatedAt(rs.getTimestamp("created_at"));

        return s;
    }
}