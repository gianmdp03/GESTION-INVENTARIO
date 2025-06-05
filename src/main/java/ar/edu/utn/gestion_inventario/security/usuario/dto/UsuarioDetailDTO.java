package ar.edu.utn.gestion_inventario.security.usuario.dto;

import ar.edu.utn.gestion_inventario.ENUM.TipoUsuario;

public record UsuarioDetailDTO(String username, TipoUsuario tipoUsuario) {
}
