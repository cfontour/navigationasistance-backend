package com.navigationasistance.modeloDAO;

import com.navigationasistance.interfaces.UsuariocaPuntosControlInterface;
import com.navigationasistance.mapper.UsuariocaPuntosControlRowMapper;
import com.navigationasistance.modelo.UsuariocaPuntosControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class UsuariocaPuntosControlDAO implements UsuariocaPuntosControlInterface {

    @Autowired
    JdbcTemplate template;

    @Override
    public int save(UsuariocaPuntosControl u) {
        String sql = "INSERT INTO usuarioca_puntoscontrol (nadadorruta_id, punto_control, fecha_hora) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rows = template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, u.getNadadorruta().getId());
            ps.setString(2, u.getPuntoControl());
            ps.setTimestamp(3, Timestamp.valueOf(u.getFechaHora()));
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            u.setId(keyHolder.getKey().intValue());
        }

        return rows;
    }

    @Override
    public List<UsuariocaPuntosControl> listar() {
        String sql = "SELECT * FROM usuarioca_puntoscontrol";
        return template.query(sql, new UsuariocaPuntosControlRowMapper());
    }

    @Override
    public List<UsuariocaPuntosControl> listarPorNadadorrutaId(Integer nadadorrutaId) {
        String sql = "SELECT * FROM usuarioca_puntoscontrol WHERE nadadorruta_id = ?";
        return template.query(sql, new Object[]{nadadorrutaId}, new UsuariocaPuntosControlRowMapper());
    }
}
