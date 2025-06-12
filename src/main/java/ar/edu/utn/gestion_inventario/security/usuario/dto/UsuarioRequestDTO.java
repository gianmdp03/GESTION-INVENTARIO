package ar.edu.utn.gestion_inventario.security.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public class UsuarioRequestDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public UsuarioRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
