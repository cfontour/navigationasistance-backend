package com.navigationasistance.modeloDAO;

import com.navigationasistance.interfaces.NadadorposicionInterface;
import com.navigationasistance.mapper.NadadorposicionRowMapper;
import com.navigationasistance.modelo.NadadorPosicion;
import org.springframework.beans.factory.annotation.Autowired;
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
        return template.queryForObject(sql, new Object[]{id}, new NadadorposicionRowMapper());
    }

    @Override
    public List<NadadorPosicion> listarVinculadosANadadorRutas() {
        String sql = "SELECT np.* FROM nadadorposicion np " +
                "JOIN nadadorrutas nr ON np.usuario_id = nr.usuario_id";
        return template.query(sql, new NadadorposicionRowMapper());
    }

    @Override
    public int upsertNadador(NadadorPosicion n) {
        String sql = "INSERT INTO nadadorposicion (usuario_id, nadadorlat, nadadorlng) " +
                "VALUES (?, ?, ?) " +
                "ON CONFLICT (usuario_id) DO UPDATE SET " +
                "nadadorlat = EXCLUDED.nadadorlat, " +
                "nadadorlng = EXCLUDED.nadadorlng, " +
                "fecha_ultima_actualizacion = now()";
        return template.update(sql, n.getUsuarioid(), n.getNadadorlat(), n.getNadadorlng());
    }


    @Override
    public int updNadador(NadadorPosicion n) {
        String sql="UPDATE nadadorposicion SET nadadorlat=?, nadadorlng=? WHERE usuario_id=?";
        return template.update(sql, n.getNadadorlat(), n.getNadadorlng(), n.getUsuarioid());
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
