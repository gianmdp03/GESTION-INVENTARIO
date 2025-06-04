package ar.edu.utn.gestion_inventario.security.usuario.service;

import ar.edu.utn.gestion_inventario.ENUM.TipoUsuario;
import ar.edu.utn.gestion_inventario.security.usuario.dto.UsuarioListDTO;
import ar.edu.utn.gestion_inventario.security.usuario.model.Usuario;
import ar.edu.utn.gestion_inventario.security.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static ar.edu.utn.gestion_inventario.security.usuario.validation.UsuarioValidator.*;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario crearUsuario(Usuario usuario)
    {
        comprobarSiExisteUsername(usuario.getUsername(), usuarioRepository);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        if(usuario.getTipoUsuario()==null)
        {
            usuario.setRol(TipoUsuario.EMPLEADO);
        }
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioListDTO> listarUsuarios()
    {
        List<UsuarioListDTO> lista = usuarioRepository.findAll().stream().map(usuario -> new UsuarioListDTO(usuario.getUsername(), usuario.getTipoUsuario())).toList();
        comprobarListaVacia(lista);
        return lista;
    }

    @Transactional
    public void eliminarUsuarioPorUsername(String username)
    {
        comprobarUsername(username, usuarioRepository);
        usuarioRepository.deleteByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return usuarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
