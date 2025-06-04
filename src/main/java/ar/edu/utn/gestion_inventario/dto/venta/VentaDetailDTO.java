package ar.edu.utn.gestion_inventario.dto.venta;

import ar.edu.utn.gestion_inventario.dto.detalleventa.DetalleVentaListDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record VentaDetailDTO (Long id, LocalDateTime fecha, BigDecimal total, List<DetalleVentaListDTO> detalleVentaListDTO) {}
