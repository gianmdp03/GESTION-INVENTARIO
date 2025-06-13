package ar.edu.utn.gestion_inventario.security.usuario.dto;

public record UsuarioLoginDetailDTO(String token, Long expiresIn) {}