package ar.edu.utn.gestion_inventario.security.usuario.controller;

import ar.edu.utn.gestion_inventario.security.usuario.dto.UsuarioLoginDetailDTO;
import ar.edu.utn.gestion_inventario.security.usuario.dto.UsuarioLoginRequestDTO;
import ar.edu.utn.gestion_inventario.security.usuario.dto.UsuarioRegisterDetailDTO;
import ar.edu.utn.gestion_inventario.security.usuario.dto.UsuarioRegisterRequestDTO;
import ar.edu.utn.gestion_inventario.security.usuario.model.Usuario;
import ar.edu.utn.gestion_inventario.security.usuario.service.AuthenticationService;
import ar.edu.utn.gestion_inventario.security.usuario.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UsuarioRegisterDetailDTO> register(@Valid @RequestBody UsuarioRegisterRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.crearUsuario(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioLoginDetailDTO> authenticate(@Valid @RequestBody UsuarioLoginRequestDTO dto) {
        Usuario authenticatedUser = authenticationService.autenticar(dto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        UsuarioLoginDetailDTO loginResponse = new UsuarioLoginDetailDTO(jwtToken, jwtService.getJwtExpirationTime());

        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }
}
