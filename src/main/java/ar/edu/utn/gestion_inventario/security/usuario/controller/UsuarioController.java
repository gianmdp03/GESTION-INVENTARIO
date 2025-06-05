package ar.edu.utn.gestion_inventario.security.usuario.controller;

import ar.edu.utn.gestion_inventario.security.usuario.dto.UsuarioDetailDTO;
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

    @PostMapping
    public ResponseEntity<UsuarioDetailDTO> crearUsuario(@Valid @RequestBody Usuario usuario)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crearUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDetailDTO>> listarUsuario()
    {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuarios());
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> eliminarPorUsername(@PathVariable String username)
    {
        usuarioService.eliminarUsuarioPorUsername(username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
