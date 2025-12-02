package com.navigationasistance.controler;

import com.navigationasistance.modelo.Usuario;
import com.navigationasistance.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    @Autowired
    private UsuarioService service;

    // 🔥 Variable GLOBAL para invalidar todas las sesiones de una vez
    private static volatile String SESSION_VERSION = UUID.randomUUID().toString();

    @GetMapping("/login/{id}/{password}")
    public ResponseEntity<Usuario> login(
            @PathVariable String id,
            @PathVariable String password,
            HttpSession session) {

        try {
            Usuario usuario = service.login(id, password);

            if (usuario != null) {

                // Guardamos datos de sesión
                session.setAttribute("usuarioLogueado", usuario.getId());

                // 🆕 Guardamos la versión global actual dentro de la sesión
                session.setAttribute("sessionVersion", SESSION_VERSION);

                return new ResponseEntity<>(usuario, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 🟦 CHEQUEO DE SESIÓN
    @GetMapping("/sesion/estado")
    public ResponseEntity<Void> estadoSesion(HttpSession session) {

        // usuario dentro de la sesión
        Object usuario = session.getAttribute("usuarioLogueado");

        // versión guardada cuando se creó la sesión
        String versionSesion = (String) session.getAttribute("sessionVersion");

        // versión global actual
        String versionGlobal = SESSION_VERSION;

        // reglas de validación
        if (usuario != null && versionSesion != null && versionSesion.equals(versionGlobal)) {
            return new ResponseEntity<>(HttpStatus.OK); // sesión válida
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // sesión inválida o expirada globalmente
        }
    }

    // 🟥 LOGOUT individual
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 🟥 LOGOUT GLOBAL → invalida TODAS las sesiones existentes
    @PostMapping("/logoutGlobal")
    public ResponseEntity<Void> logoutGlobal() {

        // Cambiar esta versión invalida todas las sesiones existentes automáticamente
        SESSION_VERSION = UUID.randomUUID().toString();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
