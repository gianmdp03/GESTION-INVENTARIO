package ar.edu.utn.gestion_inventario.dto.producto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class ProductoRequestDTO {
    @NotBlank(message = "El nombre del producto no puede estar vacío")
    private String nombre;

    private String descripcion;

    @NotBlank(message = "La categoría del producto no puede estar vacía")
    private String categoria;

    @PositiveOrZero(message = "El precio del producto no puede ser negativo")
    @NotNull(message = "El precio del producto no puede ser negativo")
    private BigDecimal precio;

    @NotBlank(message = "El código de barras del producto es obligatorio")
    private String codigoBarras;

    @Email(message = "Debe ingresar el email del proveedor de forma correcta")
    @NotBlank(message = "El email del proveedor no puede estar vacío")
    private String emailProveedor;

    private Long idDescuento;

    public ProductoRequestDTO() {
    }

    public ProductoRequestDTO(String nombre, String categoria, BigDecimal precio, String codigoBarras, String emailProveedor) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.codigoBarras = codigoBarras;
        this.emailProveedor = emailProveedor;
    }

    public ProductoRequestDTO(String nombre, String descripcion, String categoria, BigDecimal precio, String codigoBarras, String emailProveedor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.codigoBarras = codigoBarras;
        this.emailProveedor = emailProveedor;
    }

    public ProductoRequestDTO(String nombre, String categoria, BigDecimal precio, String codigoBarras, String emailProveedor, Long idDescuento) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.codigoBarras = codigoBarras;
        this.emailProveedor = emailProveedor;
        this.idDescuento = idDescuento;
    }

    public ProductoRequestDTO(String nombre, String descripcion, String categoria, BigDecimal precio, String codigoBarras, Long idDescuento, String emailProveedor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.codigoBarras = codigoBarras;
        this.idDescuento = idDescuento;
        this.emailProveedor = emailProveedor;
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

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getEmailProveedor() {
        return emailProveedor;
    }

    public void setEmailProveedor(String emailProveedor) {
        this.emailProveedor = emailProveedor;
    }

    public Long getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(Long idDescuento) {
        this.idDescuento = idDescuento;
    }
}

