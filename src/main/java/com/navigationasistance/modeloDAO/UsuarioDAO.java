package com.navigationasistance.modeloDAO;

import com.navigationasistance.interfaces.UsuarioInterface;
import com.navigationasistance.mapper.UsuarioRowMapper;
import com.navigationasistance.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioDAO implements UsuarioInterface {

	@Autowired
	JdbcTemplate template;

	@Override
	public List<Usuario> listar() {
		String sql = "SELECT * FROM usuario";
		List<Usuario> list = template.query(sql, new UsuarioRowMapper());
		return list;
	}

	@Override
	public Usuario listarId(String id) {
		String sql = "SELECT * FROM usuario WHERE id = ?";
		return template.queryForObject(sql, new Object[]{id}, new UsuarioRowMapper());
	}


	@Override
	public Usuario getUsuarioByEmail(String email) {
		String sql = "SELECT * FROM usuario WHERE email = ?";
		return template.queryForObject(sql, new Object[]{email}, new UsuarioRowMapper());
	}


	@Override
	public int addUsuario(Usuario u) {
		String sql = "insert into usuario(id, apellido, email, nombre, password, telefono)values(?,?,?,?,?,?)";
		return template.update(sql, u.getId(), u.getApellido(), u.getEmail(), u.getNombre(), u.getPassword(), u.getTelefono());
	}

	@Override
	public int updUsuario(Usuario u) {
		String sql="update usuario set apellido=?, email=?, nombre=?, telefono=? where id=?";
		return template.update(sql, u.getApellido(), u.getEmail(), u.getNombre(), u.getTelefono(), u.getId());
	}

	@Override
	public int cambiarPassword(Usuario u) {
		String sql = "UPDATE usuario SET password=? WHERE id=?";
		return template.update(sql, u.getPassword(), u.getId());
	}

	@Override
	public int delUsuario(String id) {
		String sql="delete from usuario where id=?";
		return template.update(sql,id);
	}

}
