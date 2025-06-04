package ar.edu.utn.gestion_inventario.security.usuario.dto;

import ar.edu.utn.gestion_inventario.ENUM.TipoUsuario;

public record UsuarioListDTO (String username, TipoUsuario tipoUsuario) {}
