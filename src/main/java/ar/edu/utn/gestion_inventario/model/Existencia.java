package ar.edu.utn.gestion_inventario.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "existencias")
public class Existencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private LocalDate fechaEntrada;

    @Column(nullable = false)
    private LocalDate fechaVencimiento;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    public Existencia() {
    }

    public Existencia(Long id) {
        this.id = id;
    }

    public Existencia(int cantidad, LocalDate fechaEntrada, LocalDate fechaVencimiento, Producto producto) {
        this.cantidad = cantidad;
        this.fechaEntrada = fechaEntrada;
        this.fechaVencimiento = fechaVencimiento;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
