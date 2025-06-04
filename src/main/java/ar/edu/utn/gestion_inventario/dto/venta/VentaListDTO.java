package ar.edu.utn.gestion_inventario.dto.venta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VentaListDTO (Long id, LocalDateTime fecha, BigDecimal total){}

