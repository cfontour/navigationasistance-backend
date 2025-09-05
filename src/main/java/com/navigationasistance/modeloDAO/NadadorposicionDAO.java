package com.navigationasistance.modeloDAO;

import com.navigationasistance.interfaces.NadadorposicionInterface;
import com.navigationasistance.mapper.NadadorposicionRowMapper;
import com.navigationasistance.modelo.NadadorPosicion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NadadorposicionDAO implements NadadorposicionInterface {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<NadadorPosicion> listar() {
        String sql = "SELECT * FROM nadadorposicion";
        List<NadadorPosicion> list = template.query(sql, new NadadorposicionRowMapper());
        return list;
    }

    @Override
    public NadadorPosicion getNadadorPosicion(String id) {
        String sql = "SELECT * FROM nadadorposicion WHERE usuario_id = ?";
        try {
            // ✅ ESTA LÍNEA DEBE IR DENTRO DE UN BLOQUE try-catch para EmptyResultDataAccessException
            return template.queryForObject(sql, new Object[]{id}, new NadadorposicionRowMapper());
        } catch (EmptyResultDataAccessException e) {
            // ✅ Si no se encuentra ningún resultado, retorna null.
            // Esto le permite al controlador manejar el 404 correctamente.
            return null;
        }
    }

    @Override
    public List<NadadorPosicion> listarVinculadosANadadorRutas() {
        String sql = "SELECT np.* FROM nadadorposicion np " +
                "JOIN nadadorrutas nr ON np.usuario_id = nr.usuario_id";
        return template.query(sql, new NadadorposicionRowMapper());
    }

    @Override
    public int upsertNadador(NadadorPosicion n) {
        String sql = "INSERT INTO nadadorposicion (usuario_id, nadadorlat, nadadorlng, bearing, fecha_ultima_actualizacion) " +
                "VALUES (?, ?, ?, ?, now() AT TIME ZONE 'America/Montevideo') " +  // ← 5 parámetros
                "ON CONFLICT (usuario_id) DO UPDATE SET " +
                "nadadorlat = EXCLUDED.nadadorlat, " +
                "nadadorlng = EXCLUDED.nadadorlng, " +
                "bearing = EXCLUDED.bearing, " +
                "fecha_ultima_actualizacion = now() AT TIME ZONE 'America/Montevideo'";
        return template.update(sql, n.getUsuarioid(), n.getNadadorlat(), n.getNadadorlng(), n.getBearing());
    }


    @Override
    public int updNadador(NadadorPosicion n) {
        String sql="UPDATE nadadorposicion SET nadadorlat=?, nadadorlng=?, bearing=? WHERE usuario_id=?";
        return template.update(sql, n.getNadadorlat(), n.getNadadorlng(), n.getBearing(), n.getUsuarioid());
    }

    @Override
    public int updateEmergency(NadadorPosicion n) {
        String sql="UPDATE nadadorposicion SET emergency=? WHERE usuario_id=?";
        return template.update(sql, n.getEmergency(), n.getUsuarioid());
    }

    @Override
    public int delNadador(String id) {
        String sql="DELETE FROM nadadorposicion WHERE usuario_id=?";
        return template.update(sql, id);
    }
}
