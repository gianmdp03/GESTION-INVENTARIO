package ar.edu.utn.gestion_inventario.dto.existencia;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class ExistenciaRequestDTO {
    @NotNull
    private int cantidad;
    @NotNull
    @PastOrPresent
    private LocalDate fechaEntrada;
    @NotNull
    private LocalDate fechaVencimiento;
    @NotNull
    private String codigoBarras;

    public ExistenciaRequestDTO(int cantidad, LocalDate fechaEntrada, LocalDate fechaVencimiento, String codigoBarras) {
        this.cantidad = cantidad;
        this.fechaEntrada = fechaEntrada;
        this.fechaVencimiento = fechaVencimiento;
        this.codigoBarras = codigoBarras;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }
}

