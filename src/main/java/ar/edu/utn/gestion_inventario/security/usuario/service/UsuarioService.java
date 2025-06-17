package ar.edu.utn.gestion_inventario.security.usuario.service;

import ar.edu.utn.gestion_inventario.exception.NotFoundException;
import ar.edu.utn.gestion_inventario.security.usuario.dto.UsuarioLoginDetailDTO;
import ar.edu.utn.gestion_inventario.security.usuario.dto.UsuarioLoginRequestDTO;
import ar.edu.utn.gestion_inventario.security.usuario.dto.UsuarioRegisterDetailDTO;
import ar.edu.utn.gestion_inventario.security.usuario.dto.UsuarioRegisterRequestDTO;
import ar.edu.utn.gestion_inventario.security.usuario.enums.Rol;
import ar.edu.utn.gestion_inventario.security.usuario.model.Usuario;
import ar.edu.utn.gestion_inventario.security.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static ar.edu.utn.gestion_inventario.security.usuario.validation.UsuarioValidator.*;

@Service
public class UsuarioService {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UsuarioRegisterDetailDTO crearUsuarioSinAutorizacion(UsuarioRegisterRequestDTO dto)
    {
        if(dto.getRol().equalsIgnoreCase("ADMINISTRADOR") || dto.getRol().equalsIgnoreCase("EMPLEADO"))
        {
            dto.setRol(dto.getRol().toUpperCase());
        }
        else
        {
            dto.setRol("EMPLEADO");
        }
        Usuario usuario = new Usuario(dto.getNombre(), dto.getApellido(), dto.getUsername(), passwordEncoder.encode(dto.getPassword()), Rol.valueOf(dto.getRol()));
        usuario = usuarioRepository.save(usuario);

        return new UsuarioRegisterDetailDTO(usuario.getNombre(), usuario.getApellido(), usuario.getUsername(), usuario.getRol());
    }

    public UsuarioRegisterDetailDTO crearUsuario(UsuarioRegisterRequestDTO dto) {
        if(dto.getRol().equalsIgnoreCase("ADMINISTRADOR") || dto.getRol().equalsIgnoreCase("EMPLEADO"))
        {
            dto.setRol(dto.getRol().toUpperCase());
        }
        else
        {
            dto.setRol("EMPLEADO");
        }
        Usuario usuario = new Usuario(dto.getNombre(), dto.getApellido(), dto.getUsername(), passwordEncoder.encode(dto.getPassword()), Rol.valueOf(dto.getRol()));
        usuario = usuarioRepository.save(usuario);

        return new UsuarioRegisterDetailDTO(usuario.getNombre(), usuario.getApellido(), usuario.getUsername(), usuario.getRol());
    }

    public UsuarioLoginDetailDTO obtenerToken(UsuarioLoginRequestDTO dto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        Usuario usuario = usuarioRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new NotFoundException("El nombre de usuario no existe"));

        String jwtToken = jwtService.generateToken(usuario);

        return new UsuarioLoginDetailDTO(jwtToken, jwtService.getJwtExpirationTime());
    }

    @Transactional
    public UsuarioRegisterDetailDTO modificarUsername(String usernameActual, String usernameNuevo)
    {
        comprobarSiExisteUsername(usernameNuevo, usuarioRepository);
        return usuarioRepository.findByUsername(usernameActual).map(user -> {
            user.setUsername(usernameNuevo);
            user = usuarioRepository.save(user);
            return new UsuarioRegisterDetailDTO(user.getNombre(), user.getApellido(), user.getUsername(), user.getRol());
        }).orElseThrow(() -> new NotFoundException("El nombre de usuario ingresado no corresponde a un usuario existente"));
    }

    public List<UsuarioRegisterDetailDTO> listarUsuarios()
    {
        List<UsuarioRegisterDetailDTO> lista = usuarioRepository.findAll().stream().map(usuario ->
                new UsuarioRegisterDetailDTO(usuario.getNombre(), usuario.getApellido(), usuario.getUsername(), usuario.getRol())).toList();
        comprobarListaVacia(lista);
        return lista;
    }

    public UsuarioRegisterDetailDTO convertirEnAdministrador(String username)
    {
        return usuarioRepository.findByUsername(username).map(usuario -> {
            usuario.setRol(Rol.ADMINISTRADOR);
            usuario = usuarioRepository.save(usuario);
            return new UsuarioRegisterDetailDTO(usuario.getNombre(), usuario.getApellido(), usuario.getUsername(), usuario.getRol());
        }).orElseThrow(() -> new NotFoundException("El nombre de usuario ingresado no corresponde a un usuario existente"));
    }

    public UsuarioRegisterDetailDTO mostrarUsuarioPorUsername(String username)
    {
        return usuarioRepository.findByUsername(username).map(usuario ->
                new UsuarioRegisterDetailDTO(usuario.getNombre(), usuario.getApellido(), usuario.getUsername(), usuario.getRol()))
                .orElseThrow(() -> new NotFoundException("El username ingresado no existe"));
    }
}