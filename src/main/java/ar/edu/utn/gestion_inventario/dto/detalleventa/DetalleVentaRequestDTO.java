package ar.edu.utn.gestion_inventario.dto.detalleventa;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class DetalleVentaRequestDTO {
    @NotNull
    private int cantidad;
    @NotNull
    private BigDecimal precioUnitario;
    @NotNull
    private Long idVenta; //Usar primero post de venta
    @NotNull
    private String codigoBarras;

    public DetalleVentaRequestDTO(int cantidad, BigDecimal precioUnitario, Long idVenta, String codigoBarras) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.idVenta = idVenta;
        this.codigoBarras = codigoBarras;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }
}
