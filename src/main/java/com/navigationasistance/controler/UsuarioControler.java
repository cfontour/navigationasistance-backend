package com.navigationasistance.controler;

import com.navigationasistance.modelo.Usuario;
import com.navigationasistance.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControler {
	@Autowired
	private UsuarioService service;

	public UsuarioControler() {
	}
    // muestra todos los usuario
	@GetMapping("/listar")
	public ResponseEntity<List<Usuario>> listar() {
		try {
			List<Usuario> lista = service.listar();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/listar/{idSolicitante}")
	public ResponseEntity<?> listarConRol(@PathVariable String idSolicitante) {
		Usuario solicitante = service.listarId(idSolicitante);

		if (!"ADMINISTRADOR".equalsIgnoreCase(solicitante.getRol())) {
			return new ResponseEntity<>("Acceso no autorizado", HttpStatus.FORBIDDEN);
		}

		List<Usuario> lista = service.listar();
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}

	@GetMapping("/listarId/{id}")
	public ResponseEntity<Usuario> listarId(@PathVariable String id) {
		try {
			Usuario usuario = service.listarId(id);
			if (usuario != null) {
				return new ResponseEntity<>(usuario, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getUsuarioByEmail/{email}")
	public ResponseEntity<Usuario> getUsuarioByEmail(@PathVariable String email) {
		try {
			Usuario usuario = service.getUsuarioByEmail(email);
			if (usuario != null) {
				return new ResponseEntity<>(usuario, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PostMapping("/agregar")
	public String addUsuario(@RequestBody Usuario u, Model model) { //save(@RequestBody Persona p,Model model)
		int id = service.addUsuario(u);
		if (id == 0) {
			return "No se pudo Regsitrar!";
		}
		return "Se registró con éxito!";
	}

	@PostMapping("/actualizar/{id}")
	public String save(@RequestBody Usuario u, @PathVariable String id, Model model) {
		u.setId(id);
		int r = service.updUsuario(u);
		if (r == 0) {
			return "No se pudo Actualizar!";
		}
		return "Se actualizó con éxito!";
	}

	@PostMapping("/cambiarPassword/{id}")
	public String cambiarPassword(@RequestBody Usuario u, @PathVariable String id, Model model) {
		u.setId(id);
		int r = service.cambiarPassword(u);
		if (r == 0) {
			return "Error al actualizar contraseña!";
		}
		return "Contraseña actualizada";
	}

	@DeleteMapping("/eliminar/{id}")
	public String delete(@PathVariable String id, Model model) {
		int r = service.delUsuario(id);
		if (r == 0) {
			return "Registro No Eliminado!";
		}
		return "Registro Eliminado!";
	}

}
