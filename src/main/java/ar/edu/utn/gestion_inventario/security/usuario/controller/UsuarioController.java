package ar.edu.utn.gestion_inventario.security.usuario.controller;

import ar.edu.utn.gestion_inventario.security.usuario.dto.*;
import ar.edu.utn.gestion_inventario.security.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/login")
    public ResponseEntity<UsuarioLoginDetailDTO> obtenerToken(@Valid @RequestBody UsuarioLoginRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.obtenerToken(dto));
    }
    
    @PatchMapping("/username")
    public ResponseEntity<UsuarioRegisterDetailDTO> modificarUsername(@Valid @RequestBody UsuarioPatchDTO dto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.modificarUsername(dto.getUsernameActual(), dto.getUsernameNuevo()));
    }
}
