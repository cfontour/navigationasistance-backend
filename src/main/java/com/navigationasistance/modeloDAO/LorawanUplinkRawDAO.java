package com.navigationasistance.modeloDAO;

import com.navigationasistance.interfaces.LorawanUplinkRawInterface;
import com.navigationasistance.mapper.LorawanUplinkRawRowMapper;
import com.navigationasistance.modelo.LorawanUplinkRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LorawanUplinkRawDAO implements LorawanUplinkRawInterface {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<LorawanUplinkRaw> listar() {
        String sql = "SELECT * FROM lorawan_uplink_raw";
        return template.query(sql, new LorawanUplinkRawRowMapper());
    }

    @Override
    public LorawanUplinkRaw listarClave(String devEui) {
        String sql = "SELECT * FROM lorawan_uplink_raw WHERE dev_eui = ? LIMIT 1";
        return template.queryForObject(sql, new Object[]{devEui}, new LorawanUplinkRawRowMapper());
    }

    @Override
    public int add(LorawanUplinkRaw obj) {
        String sql = "INSERT INTO lorawan_uplink_raw(received_at, device_id, dev_eui, join_eui, application_id, gateway_id, f_port, frm_payload, decoded_payload_json, raw_json) VALUES (?,?,?,?,?,?,?,?,?,?)";

        return template.update(sql,
                obj.getReceivedAt(),
                obj.getDeviceId(),
                obj.getDevEui(),
                obj.getJoinEui(),
                obj.getApplicationId(),
                obj.getGatewayId(),
                obj.getFPort(),
                obj.getFrmPayload(),
                obj.getDecodedPayloadJson(),
                obj.getRawJson()
        );
    }

    @Override
    public int upd(LorawanUplinkRaw obj) {
        String sql = "UPDATE lorawan_uplink_raw SET received_at=?, device_id=?, join_eui=?, application_id=?, gateway_id=?, f_port=?, frm_payload=?, decoded_payload_json=?, raw_json=? WHERE dev_eui=?";

        return template.update(sql,
                obj.getReceivedAt(),
                obj.getDeviceId(),
                obj.getJoinEui(),
                obj.getApplicationId(),
                obj.getGatewayId(),
                obj.getFPort(),
                obj.getFrmPayload(),
                obj.getDecodedPayloadJson(),
                obj.getRawJson(),
                obj.getDevEui()
        );
    }

    @Override
    public int del(String devEui) {
        String sql = "DELETE FROM lorawan_uplink_raw WHERE dev_eui=?";
        return template.update(sql, devEui);
    }
}
