package ar.edu.utn.gestion_inventario.security.usuario.controller;

import ar.edu.utn.gestion_inventario.security.usuario.dto.UsuarioDetailDTO;
import ar.edu.utn.gestion_inventario.security.usuario.dto.UsuarioPutRequestDTO;
import ar.edu.utn.gestion_inventario.security.usuario.model.Usuario;
import ar.edu.utn.gestion_inventario.security.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/admin")
    public ResponseEntity<UsuarioDetailDTO> crearUsuarioSinAutorizacion(@Valid @RequestBody Usuario usuario)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crearUsuario(usuario));
    }

    @PostMapping
    public ResponseEntity<UsuarioDetailDTO> crearUsuario(@Valid @RequestBody Usuario usuario)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crearUsuario(usuario));
    }

    @PutMapping
    public ResponseEntity<UsuarioDetailDTO> modificarUsuario(@Valid @RequestBody UsuarioPutRequestDTO dto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.modificarUsername(dto.getUsernameActual(), dto.getUsernameNuevo()));
    }

    @PatchMapping("/{username}")
    public ResponseEntity<UsuarioDetailDTO> convertirEnAdministrador(@RequestBody String username)
    {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.convertirEnAdministrador(username));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDetailDTO>> listarUsuario()
    {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuarios());
    }

    @GetMapping("/{username}")
    public ResponseEntity<UsuarioDetailDTO> mostrarUsuarioPorUsername(@PathVariable String username)
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
