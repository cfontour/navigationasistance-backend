package com.navigationasistance.modeloDAO;

import com.navigationasistance.interfaces.NadadorHistoricoRutasInterface;
import com.navigationasistance.mapper.NadadorhistoricoRutasRowMapper;
import com.navigationasistance.modelo.NadadorHistoricoRutas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public class NadadorHistoricoRutasDAO implements NadadorHistoricoRutasInterface {

    @Autowired
    JdbcTemplate template;

    @Override
    public int insertarRuta(NadadorHistoricoRutas r) {
        String sql = "INSERT INTO nadadorhistoricorutas " +
                "(usuario_id, recorrido_id, nadadorfecha, nadadorhora, secuencia, nadadorlat, nadadorlng) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rows = template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, r.getUsuarioid());
            ps.setObject(2, r.getRecorridoid());
            ps.setObject(3, r.getNadadorfecha());
            ps.setTimestamp(4, r.getNadadorhora());
            ps.setInt(5, r.getSecuencia());
            ps.setString(6, r.getNadadorlat());
            ps.setString(7, r.getNadadorlng());
            return ps;
        }, keyHolder);

        try {
            Number key = keyHolder.getKey();
            if (key != null) {
                r.setId(key.longValue());
            }
        } catch (Exception e) {
            // Si getKey() falla (ej. porque la base devuelve múltiples claves o columnas),
            // evitamos que la excepción se propague y continuamos sin setear el ID.
            // Esto no afecta el INSERT, solo evita el uso del valor retornado.
            System.err.println("⚠️ No se pudo obtener ID generado automáticamente (posible clave compuesta)");
        }

        return rows;
    }

    @Override
    public List<NadadorHistoricoRutas> obtenerPorUsuarioYFecha(String usuarioId, LocalDate fecha) {
        String sql = "SELECT * FROM nadadorhistoricorutas " +
                "WHERE usuario_id = ? AND nadadorfecha = ? " +
                "ORDER BY recorrido_id, secuencia";
        return template.query(sql, new Object[]{usuarioId, fecha}, new NadadorhistoricoRutasRowMapper());
    }

    @Override
    public List<UUID> obtenerRecorridosPorFecha(String usuarioId, LocalDate fecha) {
        String sql = "SELECT DISTINCT recorrido_id FROM nadadorhistoricorutas " +
                "WHERE usuario_id = ? AND nadadorfecha = ? " +
                "ORDER BY recorrido_id";
        return template.query(sql, new Object[]{usuarioId, fecha},
                (rs, rowNum) -> UUID.fromString(rs.getString("recorrido_id")));
    }

    @Override
    public List<UUID> obtenerUltimoRecorrido(String usuarioId, LocalDate fecha) {
        String sql = "SELECT recorrido_id FROM public.nadadorhistoricorutas " +
                "WHERE usuario_id = ? AND nadadorfecha = ? " +
                "ORDER BY ultima_actualizacion_ts DESC " + // <-- ¡CAMBIO CLAVE AQUÍ!
                "LIMIT 1;";
        return template.query(sql, new Object[]{usuarioId, fecha},
                (rs, rowNum) -> UUID.fromString(rs.getString("recorrido_id")));
    }

    @Override
    public List<NadadorHistoricoRutas> obtenerPorRecorridoId(UUID recorridoId) {
        String sql = "SELECT * FROM nadadorhistoricorutas " +
                "WHERE recorrido_id = ? ORDER BY secuencia";
        return template.query(sql, new Object[]{recorridoId}, new NadadorhistoricoRutasRowMapper());
    }

    @Override
    public List<NadadorHistoricoRutas> listar() {
        String sql = "SELECT * FROM nadadorhistoricorutas ORDER BY usuario_id, recorrido_id, secuencia";
        return template.query(sql, new NadadorhistoricoRutasRowMapper());
    }
}
