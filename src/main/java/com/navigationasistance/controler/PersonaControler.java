package com.navigationasistance.controler;

import com.navigationasistance.modelo.Persona;
import com.navigationasistance.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaControler {
	
	@Autowired
	private PersonaService service;

	@GetMapping("/listar")
	public ResponseEntity<List<Persona>> listar() {
		try {
			List<Persona> lista = service.listar();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/listarId/{id}")
	public ResponseEntity<Persona> listarId(@PathVariable String id) {
		try {
			Persona persona = service.listarId(id);
			if (persona != null) {
				return new ResponseEntity<>(persona, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/agregar")
	public String addPersona(@RequestBody Persona p,Model model) { //save(@RequestBody Persona p,Model model)
		int id=service.addPersona(p);
		if(id==0) {
			return "No se pudo Regsitrar!";
		}
		return "Se registró con éxito!";
	}
	
	@PostMapping("/actualizar/{id}")
	public String save(@RequestBody Persona p,@PathVariable String id,Model model) {
		p.setId(id);
		int r=service.updPersona(p);
		if(r==0) {
			return "No se pudo Actualizar!";
		}
		return "Se actualizó con éxito!";
	}
	@PostMapping("/eliminar/{id}")
	public String delete(@PathVariable String id,Model model) {
		int r=service.delPersona(id);
		if(r==0) {
			return "Registro No Eliminado!";
		}
		return "Registro Eliminado!";
	}
	
}
