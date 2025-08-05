package com.navigationasistance.service;

import com.navigationasistance.interfaces.PersonaInterface;
import com.navigationasistance.modelo.Persona;
import com.navigationasistance.modeloDAO.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonaService implements PersonaInterface {

	@Autowired
	PersonaDAO dao;
	
	@Override
	public List<Persona> listar() {return dao.listar();	}

	@Override
	public Persona listarId(String id) {
		return dao.listarId(id);
	}

	@Override
	public int addPersona(Persona p) {
		return dao.addPersona(p);
	}

	@Override
	public int updPersona(Persona p) {return dao.updPersona(p);
	}

	@Override
	public int delPersona(String id) {
		return dao.delPersona(id);
	}



}
