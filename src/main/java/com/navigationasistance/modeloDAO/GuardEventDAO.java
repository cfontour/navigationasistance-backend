package com.navigationasistance.modeloDAO;

import com.navigationasistance.interfaces.GuardEventInterface;
import com.navigationasistance.mapper.GuardEventRowMapper;
import com.navigationasistance.modelo.GuardEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GuardEventDAO implements GuardEventInterface {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<GuardEvent> listar() {
        String sql = "SELECT * FROM guardEvent";
        List<GuardEvent> list = template.query(sql, new GuardEventRowMapper());
        return list;
    }

    @Override
    public GuardEvent listarId(Integer id) {
        String sql = "SELECT * FROM guardEvent WHERE id = ?";
        return template.queryForObject(sql, new Object[]{id}, new GuardEventRowMapper());
    }

    @Override
    public int addGuardEvent(GuardEvent g) {
        String sql = "insert into guardEvent(id, usuario_id, localidad_id, type_id, event_descripcion, event_image, event_datetime)values(?,?,?,?,?,?,?)";
        return template.update(sql, g.getId(), g.getUsuario_id(), g.getLocalidad_id(), g.getType_id(), g.getEvent_descripcion(), g.getEvent_image(), g.getEvent_datetime());
    }

    @Override
    public int updGuardEvent(GuardEvent g) {
        String sql="update guardEvent set usuario_id=?, localidad_id=?, type_id=?, event_descripcion=?, event_image=?, event_datetime=? where id=?";
        return template.update(sql, g.getUsuario_id(), g.getLocalidad_id(), g.getType_id(), g.getEvent_descripcion(), g.getEvent_image(), g.getEvent_datetime());
    }

    @Override
    public int delGuardEvent(Integer id) {
        String sql="delete from guardEvent where id=?";
        return template.update(sql, id);
    }

}
