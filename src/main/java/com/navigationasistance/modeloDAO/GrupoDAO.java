package com.navigationasistance.modeloDAO;

import com.navigationasistance.interfaces.GrupoInterface;
import com.navigationasistance.mapper.GrupoRowMapper;
import com.navigationasistance.modelo.Grupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GrupoDAO implements GrupoInterface {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<Grupo> listar() {
        String sql = "SELECT * FROM grupousuarios";
        List<Grupo> list = template.query(sql, new GrupoRowMapper());
        return list;
    }

    @Override
    public Grupo listarGrupo(String grupo) {
        String sql = "SELECT * FROM grupousuarios WHERE grupoid = ?";
        return template.queryForObject(sql, new Object[]{grupo}, new GrupoRowMapper());
    }

    @Override
    public int addGrupo(Grupo g) {
        String sql = "insert into grupousuarios(grupoid, gruponombre, grupodescripcion) values(?,?,?)";
        return template.update(sql, g.getGrupoid(), g.getGruponombre(), g.getGrupodescripcion());
    }

    @Override
    public int updGrupo(Grupo g) {
        String sql="update grupousuarios set gruponombre=?, grupodescripcion=? where grupoid=?";
        return template.update(sql,g.getGruponombre(), g.getGrupodescripcion(), g.getGrupoid());
    }

    @Override
    public int delGrupo(String grupo) {
        String sql="delete from grupousuarios where grupoid=?";
        return template.update(sql,grupo);
    }

}
