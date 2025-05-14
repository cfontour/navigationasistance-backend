package com.navigationasistance.service;

import com.navigationasistance.interfaces.UsuarioInterface;
import com.navigationasistance.modelo.Usuario;
import com.navigationasistance.modeloDAO.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService implements UsuarioInterface {

	@Autowired
	UsuarioDAO dao;

	@Override
	public List<Usuario> listar() {
	  return dao.listar();
	}

	@Override
	public Usuario listarId(String id) {
		return dao.listarId(id);
	}

	@Override
	public Usuario getUsuarioByEmail(String email) {
		return dao.getUsuarioByEmail(email);}

	@Override
	public int addUsuario(Usuario u) {
		return dao.addUsuario(u);
	}

	@Override
	public int updUsuario(Usuario u) {
		return dao.updUsuario(u);
	}

	@Override
	public int delUsuario(String id) { return dao.delUsuario(id); }
}
