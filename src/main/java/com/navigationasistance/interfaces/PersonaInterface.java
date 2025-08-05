package com.navigationasistance.interfaces;

import java.util.List;

import com.navigationasistance.modelo.Persona;

public interface PersonaInterface{
	List<Persona> listar();

	Persona listarId(String id);

	int addPersona(Persona p);

	int updPersona(Persona p);

	int delPersona(String id);

}
