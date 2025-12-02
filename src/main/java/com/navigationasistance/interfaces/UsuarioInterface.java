package com.navigationasistance.interfaces;

import com.navigationasistance.modelo.Usuario;

import java.util.List;

public interface UsuarioInterface {
	List<Usuario> listar();

	List<Usuario> listarGrupo(String grupo);

	Usuario listarId(String id);

	Usuario getUsuarioByEmail(String email);

	int addUsuario(Usuario u);

	int updUsuario(Usuario u);

	int cambiarPassword(Usuario u);

	int delUsuario(String id);

	Usuario login(String id, String password);
}