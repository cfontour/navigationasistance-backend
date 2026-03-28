package com.navigationasistance.mapper;

import com.navigationasistance.modelo.LorawanUplinkRaw;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LorawanUplinkRawRowMapper implements RowMapper<LorawanUplinkRaw> {

    @Override
    public LorawanUplinkRaw mapRow(ResultSet rs, int rowNum) throws SQLException {
        LorawanUplinkRaw obj = new LorawanUplinkRaw();

        obj.setReceivedAt(toLocalDateTime(rs.getTimestamp("received_at")));
        obj.setDeviceId(rs.getString("device_id"));
        obj.setDevEui(rs.getString("dev_eui"));
        obj.setJoinEui(rs.getString("join_eui"));
        obj.setApplicationId(rs.getString("application_id"));
        obj.setGatewayId(rs.getString("gateway_id"));

        int fPort = rs.getInt("f_port");
        if (!rs.wasNull()) {
            obj.setFPort(fPort);
        }

        obj.setFrmPayload(rs.getString("frm_payload"));
        obj.setDecodedPayloadJson(rs.getString("decoded_payload_json"));
        obj.setRawJson(rs.getString("raw_json"));

        return obj;
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }
}