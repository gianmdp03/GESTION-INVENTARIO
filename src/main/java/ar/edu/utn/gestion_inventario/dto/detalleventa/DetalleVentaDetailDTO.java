package ar.edu.utn.gestion_inventario.dto.detalleventa;

import ar.edu.utn.gestion_inventario.dto.producto.ProductoDetailDTO;

import java.math.BigDecimal;

public record DetalleVentaDetailDTO (Long id, int cantidad, BigDecimal precioUnitario, Long idVenta, ProductoDetailDTO producto){}
