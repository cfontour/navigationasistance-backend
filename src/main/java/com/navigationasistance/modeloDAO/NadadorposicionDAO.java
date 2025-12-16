package com.navigationasistance.modeloDAO;

import com.navigationasistance.interfaces.NadadorposicionInterface;
import com.navigationasistance.mapper.NadadorposicionRowMapper;
import com.navigationasistance.modelo.NadadorPosicion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

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
    public List<NadadorPosicion> listarVinculadosAGrupo(String grupoId) {
        String sql = "SELECT np.* FROM nadadorposicion np " +
                "JOIN usuario u ON np.usuario_id = u.id AND u.grupoid = ?";
        return template.query(sql, new Object[]{grupoId}, new NadadorposicionRowMapper());
    }

    @Override
    public int upsertNadador(NadadorPosicion n) {
        String sql = "INSERT INTO nadadorposicion (usuario_id, nadadorlat, nadadorlng, bearing) " +
                "VALUES (?, ?, ?, ?) " +
                "ON CONFLICT (usuario_id) DO UPDATE SET " +
                "nadadorlat = EXCLUDED.nadadorlat, " +
                "nadadorlng = EXCLUDED.nadadorlng, " +
                "bearing = EXCLUDED.bearing, " +
                "fecha_ultima_actualizacion = CURRENT_TIMESTAMP AT TIME ZONE 'America/Montevideo'";

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

    @Override
    public Double calcularVelocidad(String usuarioId, UUID recorridoId) {
        String sql = "SELECT ST_Distance(" +
                "ST_SetSRID(ST_MakePoint(h1.nadadorlng::float, h1.nadadorlat::float), 4326), " +
                "ST_SetSRID(ST_MakePoint(h2.nadadorlng::float, h2.nadadorlat::float), 4326) " +
                ") / EXTRACT(EPOCH FROM (h1.nadadorhora - h2.nadadorhora)) * 3.6 as velocidad_kmh " +
                "FROM nadadorhistoricorutas h1 " +
                "JOIN nadadorhistoricorutas h2 ON h1.usuario_id = h2.usuario_id " +
                "AND h1.recorrido_id = h2.recorrido_id AND h1.secuencia = h2.secuencia + 1 " +
                "WHERE h1.usuario_id = ? AND h1.recorrido_id = ? " +
                "ORDER BY h1.secuencia DESC LIMIT 1";

        return template.queryForObject(sql, new Object[]{usuarioId, recorridoId.toString()}, Double.class);
    }

    @Override
    public List<NadadorPosicion> nadadoresCercanos(String usuarioId, String latitud, String longitud, Double distanciaMetros) {
        String sql = "SELECT np.* FROM nadadorposicion np " +
                "WHERE np.usuario_id != ? " +
                "AND ST_Distance(ST_Point(np.nadadorlng::float, np.nadadorlat::float), " +
                "ST_Point(?::float, ?::float)) < ?";

        return template.query(sql, new Object[]{usuarioId, longitud, latitud, distanciaMetros}, new NadadorposicionRowMapper());
    }

}
