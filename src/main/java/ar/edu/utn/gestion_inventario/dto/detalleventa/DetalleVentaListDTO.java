package ar.edu.utn.gestion_inventario.dto.detalleventa;

import java.math.BigDecimal;

public record DetalleVentaListDTO (Long id, int cantidad, BigDecimal precioUnitario, Long idVenta, String nombreProducto) {}
