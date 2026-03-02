package com.navigationasistance.modeloDAO;

import com.navigationasistance.interfaces.TypeEventInterface;
import com.navigationasistance.mapper.TypeEventRowMapper;
import com.navigationasistance.modelo.TypeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class TypeEventDAO implements TypeEventInterface{

    @Autowired
    JdbcTemplate template;

    @Override
    public List<TypeEvent> listar() {
        String sql = "SELECT * FROM typeEvent";
        List<TypeEvent> list = template.query(sql, new TypeEventRowMapper());
        return list;
    }

    @Override
    public TypeEvent listarId(BigDecimal id) {
        String sql = "SELECT * FROM typeEvent WHERE id = ?";
        return template.queryForObject(sql, new Object[]{id}, new TypeEventRowMapper());
    }

    @Override
    public int addTypeEvent(TypeEvent t) {
        String sql = "insert into typeEvent(id, type_nombre, type_importancia)values(?,?,?)";
        return template.update(sql, t.getId(), t.getType_nombre(), t.getType_importancia());
    }

    @Override
    public int updTypeEvent(TypeEvent t) {
        String sql="update typeEvent set type_nombre=?, type_importancia=? where id=?";
        return template.update(sql, t.getType_nombre(), t.getType_nombre(), t.getId());
    }

    @Override
    public int delTypeEvent(BigDecimal id) {
        String sql="delete from typeEvent where id=?";
        return template.update(sql, id);
    }

}
