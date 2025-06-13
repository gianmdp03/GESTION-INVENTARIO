package ar.edu.utn.gestion_inventario.security.usuario.controller;

import ar.edu.utn.gestion_inventario.security.usuario.dto.UsuarioLoginDetailDTO;
import ar.edu.utn.gestion_inventario.security.usuario.dto.UsuarioLoginRequestDTO;
import ar.edu.utn.gestion_inventario.security.usuario.dto.UsuarioRegisterDetailDTO;
import ar.edu.utn.gestion_inventario.security.usuario.dto.UsuarioRegisterRequestDTO;
import ar.edu.utn.gestion_inventario.security.usuario.model.Usuario;
import ar.edu.utn.gestion_inventario.security.usuario.service.UsuarioService;
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
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar/admin")
    public ResponseEntity<UsuarioRegisterDetailDTO> crearUsuarioSinAutorizacion(@Valid @RequestBody UsuarioRegisterRequestDTO dto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crearUsuarioSinAutorizacion(dto));
    }

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioRegisterDetailDTO> crearUsuario(@Valid @RequestBody UsuarioRegisterRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crearUsuario(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioLoginDetailDTO> obtenerToken(@Valid @RequestBody UsuarioLoginRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.obtenerToken(dto));
    }
}
