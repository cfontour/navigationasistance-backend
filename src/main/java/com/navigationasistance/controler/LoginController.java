package com.navigationasistance.controler;

import com.navigationasistance.config.JwtUtil;
import com.navigationasistance.modelo.Usuario;
import com.navigationasistance.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private JwtUtil jwtUtil;

    // 🔐 LOGIN - Devuelve JWT
    @GetMapping("/login/{id}/{password}")
    public ResponseEntity<?> login(
            @PathVariable String id,
            @PathVariable String password) {

        try {
            Usuario usuario = service.login(id, password);

            if (usuario != null) {
                String token = jwtUtil.generateToken(usuario.getId());
                Map<String, Object> response = new HashMap<>();
                response.put("token", token);
                response.put("usuario", usuario.getId());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ✅ VERIFICAR TOKEN
    @GetMapping("/sesion/estado")
    public ResponseEntity<?> estadoSesion(@RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String token = authHeader.substring(7); // Extraer token sin "Bearer "

        if (jwtUtil.isTokenValid(token)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // 🚪 LOGOUT
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        // Con JWT, el logout es simplemente descartar el token en el frontend
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 🚪 LOGOUT GLOBAL (opcional - si necesitas invalidar todos los tokens)
    @PostMapping("/logoutGlobal")
    public ResponseEntity<Void> logoutGlobal() {
        // Con JWT stateless, no hay "logout global" fácil
        // El frontend simplemente descarta todos los tokens almacenados
        return new ResponseEntity<>(HttpStatus.OK);
    }
}