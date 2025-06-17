package ar.edu.utn.gestion_inventario.security.usuario.validation;

import ar.edu.utn.gestion_inventario.exception.ConflictException;
import ar.edu.utn.gestion_inventario.exception.NotFoundException;
import ar.edu.utn.gestion_inventario.security.usuario.dto.UsuarioRegisterDetailDTO;
import ar.edu.utn.gestion_inventario.security.usuario.repository.UsuarioRepository;

import java.util.List;

public class UsuarioValidator {
    public static void comprobarSiExisteUsername(String username, UsuarioRepository usuarioRepository)
    {
        if(usuarioRepository.existsByUsername(username))
        {
            throw new ConflictException("El nombre de usuario ingresado ya existe");
        }
    }

    public static void comprobarUsername(String username, UsuarioRepository usuarioRepository)
    {
        if(!(usuarioRepository.existsByUsername(username)))
        {
            throw new NotFoundException("El nombre de usuario no existe");
        }
    }

    public static void comprobarListaVacia(List<UsuarioRegisterDetailDTO> usuarios)
    {
        if(usuarios.isEmpty())
        {
            throw new NotFoundException("No hay usuarios cargados en el sistema");
        }
    }
}
