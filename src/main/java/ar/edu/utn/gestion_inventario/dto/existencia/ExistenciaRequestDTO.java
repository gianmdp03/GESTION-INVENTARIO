package ar.edu.utn.gestion_inventario.dto.existencia;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class ExistenciaRequestDTO {
    @NotNull(message = "La cantidad no puede estar vacía")
    private int cantidad;

    @NotNull(message = "La fecha de entrada no puede estar vacía")
    @PastOrPresent(message = "La fecha de entrada no puede ser futura")
    private LocalDate fechaEntrada;

    @NotNull(message = "La fecha de vencimiento no puede estar vacía")
    @Future(message = "La fecha de vencimiento tiene que ser futura")
    private LocalDate fechaVencimiento;

    @NotNull(message = "El ID de producto es obligatorio")
    private Long idProducto;

    public ExistenciaRequestDTO() {
    }

    public ExistenciaRequestDTO(int cantidad, LocalDate fechaEntrada, LocalDate fechaVencimiento, Long idProducto) {
        this.cantidad = cantidad;
        this.fechaEntrada = fechaEntrada;
        this.fechaVencimiento = fechaVencimiento;
        this.idProducto = idProducto;
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

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
}

