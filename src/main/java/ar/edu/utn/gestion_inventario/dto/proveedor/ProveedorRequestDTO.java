package ar.edu.utn.gestion_inventario.dto.proveedor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ProveedorRequestDTO {
    @NotBlank(message = "El nombre del proveedor no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El teléfono del proveedor es obligatorio")
    private String telefono;

    @Email(message = "Debe ingresar el email del proveedor de forma correcta")
    @NotBlank(message = "El email del proveedor no puede estar vacío")
    private String email;

    @NotBlank(message = "La dirección del proveedor es obligatoria")
    private String direccion;

    public ProveedorRequestDTO() {
    }

    public ProveedorRequestDTO(String nombre, String telefono, String email, String direccion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}

