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
import java.util.Map;

@Repository
public class UsuariocaPuntosControlDAO implements UsuariocaPuntosControlInterface {

    @Autowired
    JdbcTemplate template;

    @Override
    public int save(UsuariocaPuntosControl u) {
        // Validación: existe ya el punto para este nadadorruta_id?
        String checkSql = "SELECT COUNT(*) FROM usuarioca_puntoscontrol WHERE nadadorruta_id = ? AND punto_control = ?";
        Integer count = template.queryForObject(checkSql, new Object[]{u.getNadadorrutaId(), u.getPuntoControl()}, Integer.class);

        if (count != null && count > 0) {
            System.out.println("⚠️ Ya existe ese punto de control para el nadador. No se insertará.");
            return 0;
        }

        // Insertar si no existe
        String sql = "INSERT INTO usuarioca_puntoscontrol (nadadorruta_id, punto_control, fecha_hora, ruta_id) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rows = template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, u.getNadadorrutaId());
            ps.setString(2, u.getPuntoControl());
            ps.setTimestamp(3, Timestamp.valueOf(u.getFechaHora()));
            ps.setInt(4, u.getRutaId());
            return ps;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys != null && keys.containsKey("id")) {
            Object id = keys.get("id");
            if (id instanceof Number) {
                u.setId(((Number) id).intValue());
            }
        }

        return rows;
    }

    @Override
    public List<UsuariocaPuntosControl> listar() {
        String sql = "SELECT * FROM usuarioca_puntoscontrol";
        return template.query(sql, new UsuariocaPuntosControlRowMapper());
    }

    @Override
    public List<UsuariocaPuntosControl> listarPorNadadorrutaId(String nadadorrutaId) {
        // Este método probablemente deba cambiarse a String también:
        String sql = "SELECT * FROM usuarioca_puntoscontrol WHERE nadadorruta_id = ?";
        return template.query(sql, new Object[]{String.valueOf(nadadorrutaId)}, new UsuariocaPuntosControlRowMapper());
    }

    @Override
    public List<UsuariocaPuntosControl> listarPorRutaIdnadadorRutaId(Integer rutaId, String nadadorrutaId) {
        // El orden de los parámetros en new Object[]{} DEBE coincidir con el orden de los '?' en el SQL
        String sql = "SELECT * FROM usuarioca_puntoscontrol WHERE ruta_id = ? AND nadadorruta_id = ?";
        // Aquí pasamos rutaId primero (Integer) y luego nadadorrutaId (String)
        return template.query(sql, new Object[]{rutaId, nadadorrutaId}, new UsuariocaPuntosControlRowMapper());
    }

    @Override
    public List<UsuariocaPuntosControl> listarPorRutaId(Integer rutaId) {
        String sql = "SELECT * FROM usuarioca_puntoscontrol WHERE ruta_id = ?";
        // Pass the Integer directly if ruta_id in the DB is an integer
        return template.query(sql, new Object[]{rutaId}, new UsuariocaPuntosControlRowMapper());
    }

    @Override
    public int deleteUsuarioRutasPuntos(String nadadorruta_id) {
        String sql="delete from usuarioca_puntoscontrol where nadadorruta_id =?";
        return template.update(sql, nadadorruta_id);
    }

}
