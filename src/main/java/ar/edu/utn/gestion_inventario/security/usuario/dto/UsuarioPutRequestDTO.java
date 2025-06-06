package ar.edu.utn.gestion_inventario.security.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public class UsuarioPutRequestDTO {
    @NotBlank
    private String usernameActual;

    @NotBlank
    private String usernameNuevo;

    public UsuarioPutRequestDTO() {
    }

    public UsuarioPutRequestDTO(String usernameActual, String usernameNuevo) {
        this.usernameActual = usernameActual;
        this.usernameNuevo = usernameNuevo;
    }

    public String getUsernameActual() {
        return usernameActual;
    }

    public void setUsernameActual(String usernameActual) {
        this.usernameActual = usernameActual;
    }

    public String getUsernameNuevo() {
        return usernameNuevo;
    }

    public void setUsernameNuevo(String usernameNuevo) {
        this.usernameNuevo = usernameNuevo;
    }
}
