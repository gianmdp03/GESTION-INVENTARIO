package ar.edu.utn.gestion_inventario.security.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public class UsuarioPatchDTO {
    @NotBlank
    private String usernameActual;

    @NotBlank
    private String usernameNuevo;

    public UsuarioPatchDTO() {
    }

    public UsuarioPatchDTO(String usernameActual, String usernameNuevo) {
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
