package ar.edu.utn.gestion_inventario.security.usuario.controller;

import ar.edu.utn.gestion_inventario.security.usuario.dto.*;
import ar.edu.utn.gestion_inventario.security.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    
    @PatchMapping("/{username}")
    public ResponseEntity<UsuarioRegisterDetailDTO> convertirEnAdministrador(@PathVariable String username)
    {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.convertirEnAdministrador(username));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioRegisterDetailDTO>> listarUsuario()
    {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuarios());
    }

    @GetMapping("/{username}")
    public ResponseEntity<UsuarioRegisterDetailDTO> mostrarUsuarioPorUsername(@PathVariable String username)
    {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.mostrarUsuarioPorUsername(username));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> eliminarPorUsername(@PathVariable String username)
    {
        usuarioService.eliminarUsuarioPorUsername(username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
