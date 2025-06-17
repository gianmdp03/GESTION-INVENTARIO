package ar.edu.utn.gestion_inventario.security.usuario.validation;

import ar.edu.utn.gestion_inventario.exception.ConflictException;
import ar.edu.utn.gestion_inventario.security.usuario.repository.UsuarioRepository;

public class UsuarioValidator {
    public static void comprobarSiExisteUsername(String username, UsuarioRepository usuarioRepository)
    {
        if(usuarioRepository.existsByUsername(username))
        {
            throw new ConflictException("El nombre de usuario ingresado ya existe");
        }
    }
}
