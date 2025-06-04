package ar.edu.utn.gestion_inventario.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private int codigo;
    private String error;
    private String mensaje;
    private LocalDateTime timestamp;

    public ErrorResponse(int codigo, String error, String mensaje) {
        this.codigo = codigo;
        this.error = error;
        this.mensaje = mensaje;
        this.timestamp = LocalDateTime.now();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

