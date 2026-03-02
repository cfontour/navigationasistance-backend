package com.navigationasistance.modeloDAO;

import com.navigationasistance.interfaces.LocalidadInterface;
import com.navigationasistance.mapper.LocalidadRowMapper;
import com.navigationasistance.modelo.Localidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class LocalidadDAO implements LocalidadInterface {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<Localidad> listar() {
        String sql = "SELECT * FROM localidad";
        List<Localidad> list = template.query(sql, new LocalidadRowMapper());
        return list;
    }

    @Override
    public Localidad listarId(BigDecimal id) {
        String sql = "SELECT * FROM localidad WHERE id = ?";
        return template.queryForObject(sql, new Object[]{id}, new LocalidadRowMapper());
    }

    @Override
    public int addLocalidad(Localidad l) {
        String sql = "insert into localidad(id, localidad_pais, localidad_nombre)values(?,?,?)";
        return template.update(sql, l.getId(), l.getLocalidad_pais(), l.getLocalidad_nombre());
    }

    @Override
    public int updLocalidad(Localidad l) {
        String sql="update localidad set localidad_pais=?, localidad_nombre=? where id=?";
        return template.update(sql, l.getLocalidad_pais(), l.getLocalidad_nombre(), l.getId());
    }

    @Override
    public int delLocalidad(BigDecimal id) {
        String sql="delete from localidad where id=?";
        return template.update(sql, id);
    }

}
