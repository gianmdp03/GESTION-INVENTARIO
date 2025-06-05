package ar.edu.utn.gestion_inventario.security.usuario.validation;

import ar.edu.utn.gestion_inventario.exception.ConflictException;
import ar.edu.utn.gestion_inventario.exception.NotFoundException;
import ar.edu.utn.gestion_inventario.security.usuario.dto.UsuarioDetailDTO;
import ar.edu.utn.gestion_inventario.security.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UsuarioValidator {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void comprobarSiExisteUsername(String username)
    {
        if(usuarioRepository.existsByUsername(username))
        {
            throw new ConflictException("El nombre de usuario ingresado ya existe");
        }
    }
    public void comprobarUsername(String username)
    {
        if(!(usuarioRepository.existsByUsername(username)))
        {
            throw new NotFoundException("El nombre de usuario no existe");
        }
    }
    public void comprobarListaVacia(List<UsuarioDetailDTO> usuarios)
    {
        if(usuarios.isEmpty())
        {
            throw new NotFoundException("No hay usuarios cargados en el sistema");
        }
    }
}
