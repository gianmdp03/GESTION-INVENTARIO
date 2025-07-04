package ar.edu.utn.gestion_inventario.dto.descuento;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DescuentoRequestDTO {

    private String descripcion;

    @NotNull(message = "El porcentaje no puede estar vacío")
    private BigDecimal porcentaje;

    @FutureOrPresent(message = "La fecha de inicio no puede ser pasada")
    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @Future(message = "La fecha de fin no puede ser pasada ni presente")
    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDate fechaFin;

    private List<Long> productos = new ArrayList<>();

    public DescuentoRequestDTO() {
    }

    public DescuentoRequestDTO(BigDecimal porcentaje, LocalDate fechaInicio, LocalDate fechaFin, List<Long> productos) {
        this.porcentaje = porcentaje;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.productos = productos;
    }

    public DescuentoRequestDTO(String descripcion, BigDecimal porcentaje, LocalDate fechaInicio, LocalDate fechaFin, List<Long> productos) {
        this.descripcion = descripcion;
        this.porcentaje = porcentaje;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.productos = productos;
    }

    public DescuentoRequestDTO(String descripcion, BigDecimal porcentaje, LocalDate fechaInicio, LocalDate fechaFin) {
        this.descripcion = descripcion;
        this.porcentaje = porcentaje;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public DescuentoRequestDTO(BigDecimal porcentaje, LocalDate fechaInicio, LocalDate fechaFin) {
        this.porcentaje = porcentaje;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Long> getProductos() {
        return productos;
    }

    public void setProductos(List<Long> productos) {
        this.productos = productos;
    }
}
