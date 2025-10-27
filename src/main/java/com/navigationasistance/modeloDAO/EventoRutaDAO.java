package com.navigationasistance.modeloDAO;

import com.navigationasistance.interfaces.EventoRutaInterface;
import com.navigationasistance.mapper.EventoRutaRowMapper;
import com.navigationasistance.modelo.EventoRuta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventoRutaDAO implements EventoRutaInterface {

    @Autowired
    JdbcTemplate template;

    @Override
    public int agregar(EventoRuta eventoRuta) {
        String sql = "INSERT INTO eventoruta(usuario_id, ruta_id, evento, fecha) VALUES (?, ?, ?, ?)";
        return template.update(sql, eventoRuta.getUsuarioId(), eventoRuta.getRutaId(), eventoRuta.getEvento(), eventoRuta.getFecha());
    }

    @Override
    public List<EventoRuta> listar() {
        String sql = "SELECT * FROM eventoruta";
        return template.query(sql, new EventoRutaRowMapper());
    }

    @Override
    public List<EventoRuta> listarPorUsuario(String usuarioId) {
        String sql = "SELECT * FROM eventoruta WHERE usuario_id = ?";
        return template.query(sql, new Object[]{usuarioId}, new EventoRutaRowMapper());
    }

    @Override
    public List<EventoRuta> listarPorRuta(int rutaId) {
        String sql = "SELECT * FROM eventoruta WHERE ruta_id = ?";
        return template.query(sql, new Object[]{rutaId}, new EventoRutaRowMapper());
    }

    @Override
    public int actualizar(EventoRuta eventoRuta) {
        String sql = "UPDATE eventoruta SET evento = ?, fecha = ? WHERE id = ?";
        return template.update(sql, eventoRuta.getEvento(), eventoRuta.getFecha(), eventoRuta.getId());
    }

    @Override
    public int eliminar(int id) {
        String sql = "DELETE FROM eventoruta WHERE id = ?";
        return template.update(sql, id);
    }

    @Override
    public EventoRuta buscarPorId(int id) {
        String sql = "SELECT * FROM eventoruta WHERE id = ?";
        return template.queryForObject(sql, new Object[]{id}, new EventoRutaRowMapper());
    }
}
