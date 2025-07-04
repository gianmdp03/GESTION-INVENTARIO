package ar.edu.utn.gestion_inventario.dto.producto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class ProductoRequestPutDTO {
    @NotBlank(message = "El nombre del producto no puede estar vacío")
    private String nombre;

    private String descripcion;

    @NotBlank(message = "La categoría del producto no puede estar vacía")
    private String categoria;

    @PositiveOrZero(message = "El precio del producto no puede ser negativo")
    @NotNull(message = "El precio del producto no puede ser negativo")
    private BigDecimal precio;

    public ProductoRequestPutDTO() {
    }

    public ProductoRequestPutDTO(String nombre, String descripcion, String categoria, BigDecimal precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
