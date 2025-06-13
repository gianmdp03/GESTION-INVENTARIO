package ar.edu.utn.gestion_inventario.security.usuario.dto;

import ar.edu.utn.gestion_inventario.security.usuario.enums.Rol;

public record UsuarioRegisterDetailDTO(String nombre, String apellido, String username, Rol rol) {
}
