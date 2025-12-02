package com.navigationasistance.controler;

import com.navigationasistance.modelo.Usuario;
import com.navigationasistance.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Controlador REST para login de usuarios
@RestController
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/login/{id}/{password}")
    public ResponseEntity<Usuario> login(
            @PathVariable String id,
            @PathVariable String password) {

        try {
            Usuario usuario = service.login(id, password);

            if (usuario != null) {
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
}
