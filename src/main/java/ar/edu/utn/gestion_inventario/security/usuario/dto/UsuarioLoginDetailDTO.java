package ar.edu.utn.gestion_inventario.security.usuario.dto;

public class UsuarioLoginDetailDTO {
    private String token;

    private Long expiresIn;

    public UsuarioLoginDetailDTO() {
    }

    public UsuarioLoginDetailDTO(String token, Long expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}