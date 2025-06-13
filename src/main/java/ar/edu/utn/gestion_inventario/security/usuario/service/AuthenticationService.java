package ar.edu.utn.gestion_inventario.security.usuario.service;

import ar.edu.utn.gestion_inventario.exception.NotFoundException;
import ar.edu.utn.gestion_inventario.security.usuario.dto.UsuarioLoginRequestDTO;
import ar.edu.utn.gestion_inventario.security.usuario.dto.UsuarioRegisterRequestDTO;
import ar.edu.utn.gestion_inventario.security.usuario.model.Usuario;
import ar.edu.utn.gestion_inventario.security.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Usuario iniciarSesion(UsuarioRegisterRequestDTO dto) {
        Usuario usuario = new Usuario(dto.getNombre(), dto.getApellido(), dto.getUsername(), passwordEncoder.encode(dto.getPassword()));

        return usuarioRepository.save(usuario);
    }

    public Usuario autenticar(UsuarioLoginRequestDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                )
        );

        return usuarioRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new NotFoundException("El nombre de usuario no existe"));
    }
}