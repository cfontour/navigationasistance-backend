package com.navigationasistance.controler;

import com.navigationasistance.modelo.Usuario;
import com.navigationasistance.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

// Controlador REST para login de usuarios
@RestController
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/login/{id}/{password}")
    public ResponseEntity<Usuario> login(
            @PathVariable String id,
            @PathVariable String password, HttpSession session) {

        try {
            Usuario usuario = service.login(id, password);

            if (usuario != null) {
                // 🔹 Guardamos algo en sesión (puede ser el id o el objeto entero)
                session.setAttribute("usuarioLogueado", usuario.getId());
                // Credenciales válidas → devolvemos el usuario
                return new ResponseEntity<>(usuario, HttpStatus.OK);
            } else {
                // Usuario inexistente o password incorrecta
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // CHEQUEO DE SESIÓN: ¿hay usuario logueado?
    @GetMapping("/sesion/estado")
    public ResponseEntity<Void> estadoSesion(HttpSession session) {
        Object usuario = session.getAttribute("usuarioLogueado");
        if (usuario != null) {
            return new ResponseEntity<>(HttpStatus.OK); // sesión válida
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // no logueado
        }
    }

    // LOGOUT: invalida sesión
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
