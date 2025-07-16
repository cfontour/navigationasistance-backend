package com.navigationasistance.modeloDAO;

import com.navigationasistance.interfaces.SenialesInterface;
import com.navigationasistance.mapper.SenialesRowMapper;
import com.navigationasistance.modelo.Seniales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SenialesDAO  implements SenialesInterface {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<Seniales> listar() {
        String sql = "SELECT * FROM seniales";
        List<Seniales> list = template.query(sql, new SenialesRowMapper());
        return list;
    }

    @Override
    public Seniales listarId(Integer id) {
        String sql = "SELECT * FROM seniales WHERE id = ?";
        return template.queryForObject(sql, new Object[]{id}, new SenialesRowMapper());
    }


    @Override
    public Seniales getSenialesByRutaId(Integer rutaId) {
        String sql = "SELECT * FROM seniales WHERE ruta_id = ?";
        return template.queryForObject(sql, new Object[]{rutaId}, new SenialesRowMapper());
    }

    @Override
    public int addSeniales(Seniales s) {
        String sql = "insert into seniales(id, ruta_id, mts, latl, lngl, latr, lngr, latc, lngc, tipo)values(?,?,?,?,?,?,?,?,?,?)";
        return template.update(sql, s.getId(), s.getRuta_id(), s.getMts(), s.getLatl(), s.getLngl(), s.getLatr(), s.getLngr(), s.getLatc(), s.getLngc(), s.getTipo());
    }

    @Override
    public int updSeniales(Seniales s) {
        String sql="update seniales set ruta_id=?, mts=?, latl=?, lngl=?, latr=?, lngr=?, latc=?, lngc=?, tipo=? where id=?";
        return template.update(sql, s.getId(), s.getRuta_id(), s.getMts(), s.getLatl(), s.getLngl(), s.getLatr(), s.getLngr(), s.getLatc(), s.getLngc(), s.getTipo(), s.getId());
    }

    @Override
    public int delSeniales(Integer id) {
        String sql="delete from seniales where id=?";
        return template.update(sql,id);
    }

    @Override
    public int delSenialesPorRutaId(Integer rutaId) {
        String sql="delete from seniales where ruta_id=?";
        return template.update(sql,rutaId);
    }

}
