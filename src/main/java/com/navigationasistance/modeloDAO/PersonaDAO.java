package com.navigationasistance.modeloDAO;

import com.navigationasistance.interfaces.PersonaInterface;
import com.navigationasistance.mapper.PersonaRowMapper;
import com.navigationasistance.mapper.UsuarioRowMapper;
import com.navigationasistance.modelo.Persona;
import com.navigationasistance.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonaDAO implements PersonaInterface {

	@Autowired
	JdbcTemplate template;

	@Override
	public List<Persona> listar() {
		String sql = "SELECT * FROM persona";
		List<Persona> list = template.query(sql, new PersonaRowMapper());
		return list;
	}

	@Override
	public Persona listarId(String id) {
		String sql = "SELECT * FROM persona WHERE id = ?";
		return template.queryForObject(sql, new Object[]{id}, new PersonaRowMapper());
	}

	@Override
	public int addPersona(Persona p) {
		String sql = "insert into persona(id, nombre, apellido)values(?,?,?)";
		return template.update(sql, p.getId(), p.getNombre(), p.getApellido());
	}

	@Override
	public int updPersona(Persona p) {
		String sql="update persona set nombre=?, apellido=? where id=?";
		return template.update(sql,p.getNombre(),p.getApellido(),p.getId());
	}

	@Override
	public int delPersona(String id) {
		String sql="delete from persona where id=?";
		return template.update(sql,id);
	}

}
