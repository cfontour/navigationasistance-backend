package com.navigationasistance.modeloDAO;

import com.navigationasistance.interfaces.ParametroInterface;
import com.navigationasistance.mapper.ParametroRowMapper;
import com.navigationasistance.modelo.Parametro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParametroDAO implements ParametroInterface {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<Parametro> listar() {
        String sql = "SELECT * FROM parametro";
        List<Parametro> list = template.query(sql, new ParametroRowMapper());
        return list;
    }

    @Override
    public Parametro listarClave(String clave) {
        String sql = "SELECT * FROM parametro WHERE clave = ?";
        return template.queryForObject(sql, new Object[]{clave}, new ParametroRowMapper());
    }

    @Override
    public int addParametro(Parametro p) {
        String sql = "insert into parametro(clave, valor)values(?,?)";
        return template.update(sql, p.getClave(), p.getValor());
    }

    @Override
    public int updParametro(Parametro p) {
        String sql="update parametro set valor=? where clave=?";
        return template.update(sql,p.getValor(),p.getClave());
    }

    @Override
    public int delParametro(String clave) {
        String sql="delete from parametro where clave=?";
        return template.update(sql,clave);
    }

}
