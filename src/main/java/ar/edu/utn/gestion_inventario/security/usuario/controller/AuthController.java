package ar.edu.utn.gestion_inventario.security.usuario.controller;

import ar.edu.utn.gestion_inventario.security.configuration.JwtUtil;
import ar.edu.utn.gestion_inventario.security.usuario.dto.UsuarioRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager; // Gestiona la autenticación

    @PostMapping("/login")              // Endpoint POST /api/auth/login
    public ResponseEntity<Map<String, String>> login(@RequestBody UsuarioRequestDTO req) {
        // 1) Construir token de autenticación
        UsernamePasswordAuthenticationToken upToken =
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());

        // 2) Autenticar credenciales
        Authentication auth = authManager.authenticate(upToken);

        // 3) Generar JWT si éxito
        UserDetails user = (UserDetails) auth.getPrincipal();
        String jwt = JwtUtil.createToken(user.getUsername(),
                user.getAuthorities().stream()
                        .map(a -> a.getAuthority().replace("ROLE_", ""))
                        .collect(Collectors.toList()));

        // 4) Devolver token
        return ResponseEntity.ok(Map.of("token", jwt));
    }
}