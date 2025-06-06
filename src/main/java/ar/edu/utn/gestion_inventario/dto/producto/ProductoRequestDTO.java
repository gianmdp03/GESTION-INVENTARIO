package ar.edu.utn.gestion_inventario.dto.producto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ProductoRequestDTO {
    @NotBlank
    private String nombre;
    private String descripcion;
    @NotBlank
    private String categoria;
    @NotNull
    private BigDecimal precioUnitario;
    @NotBlank
    private String codigoBarras;
    @Email
    @NotBlank
    private String emailProveedor;

    private Long idDescuento;

    public ProductoRequestDTO() {
    }

    public ProductoRequestDTO(String nombre, String categoria, BigDecimal precioUnitario, String codigoBarras, String emailProveedor) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precioUnitario = precioUnitario;
        this.codigoBarras = codigoBarras;
        this.emailProveedor = emailProveedor;
    }

    public ProductoRequestDTO(String nombre, String descripcion, String categoria, BigDecimal precioUnitario, String codigoBarras, String emailProveedor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precioUnitario = precioUnitario;
        this.codigoBarras = codigoBarras;
        this.emailProveedor = emailProveedor;
    }

    public ProductoRequestDTO(String nombre, String categoria, BigDecimal precioUnitario, String codigoBarras, String emailProveedor, Long idDescuento) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precioUnitario = precioUnitario;
        this.codigoBarras = codigoBarras;
        this.emailProveedor = emailProveedor;
        this.idDescuento = idDescuento;
    }

    public ProductoRequestDTO(String nombre, String descripcion, String categoria, BigDecimal precioUnitario, String codigoBarras, Long idDescuento, String emailProveedor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precioUnitario = precioUnitario;
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

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
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

