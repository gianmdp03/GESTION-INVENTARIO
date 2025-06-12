package ar.edu.utn.gestion_inventario.security.usuario.dto;

import java.util.List;

public record UsuarioDetailDTO(String username, List<String> roles){}
