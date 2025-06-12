package ar.edu.utn.gestion_inventario.security.usuario.service;

import ar.edu.utn.gestion_inventario.ENUM.TipoUsuario;
import ar.edu.utn.gestion_inventario.exception.NotFoundException;
import ar.edu.utn.gestion_inventario.security.usuario.dto.UsuarioDetailDTO;
import ar.edu.utn.gestion_inventario.security.usuario.model.Usuario;
import ar.edu.utn.gestion_inventario.security.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static ar.edu.utn.gestion_inventario.security.usuario.validation.UsuarioValidator.*;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioDetailDTO crearUsuario(Usuario usuario)
    {
        comprobarSiExisteUsername(usuario.getUsername(), usuarioRepository);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        /*if(usuario.getTipoUsuario()==null)
        {
            usuario.setTipoUsuario(TipoUsuario.EMPLEADO);
        }*/
        usuario = usuarioRepository.save(usuario);
        return new UsuarioDetailDTO(usuario.getUsername(), usuario.getRoles());
    }

    @Transactional
    public UsuarioDetailDTO modificarUsername(String usernameActual, String usernameNuevo)
    {
        comprobarSiExisteUsername(usernameNuevo, usuarioRepository);
        return usuarioRepository.findByUsername(usernameActual).map(user -> {
            user.setUsername(usernameNuevo);
            user = usuarioRepository.save(user);
            return new UsuarioDetailDTO(user.getUsername(), user.getRoles());
        }).orElseThrow(() -> new NotFoundException("El nombre de usuario ingresado no corresponde a un usuario existente"));
    }

    public List<UsuarioDetailDTO> listarUsuarios()
    {
        List<UsuarioDetailDTO> lista = usuarioRepository.findAll().stream().map(usuario -> new UsuarioDetailDTO(usuario.getUsername(), usuario.getRoles())).toList();
        comprobarListaVacia(lista);
        return lista;
    }

    /*public UsuarioDetailDTO convertirEnAdministrador(String username)
    {
        return usuarioRepository.findByUsername(username).map(usuario -> {
            usuario.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
            usuario = usuarioRepository.save(usuario);
            return new UsuarioDetailDTO(usuario.getUsername(), usuario.getTipoUsuario());
        }).orElseThrow(() -> new NotFoundException("El nombre de usuario ingresado no corresponde a un usuario existente"));
    }*/

    public UsuarioDetailDTO mostrarUsuarioPorUsername(String username)
    {
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("El username ingresado no existe"));
        return new UsuarioDetailDTO(usuario.getUsername(), usuario.getRoles());
    }

    @Transactional
    public void eliminarUsuarioPorUsername(String username)
    {
        comprobarUsername(username, usuarioRepository);
        usuarioRepository.deleteByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1) Buscar usuario en BD
        Usuario user = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // 2) Convertir roles a GrantedAuthority
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                .collect(Collectors.toList());

        // 3) Crear UserDetails de Spring
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);
    }
}
