package ar.edu.utn.gestion_inventario.dto.descuento;

import ar.edu.utn.gestion_inventario.dto.producto.ProductoShortListDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record DescuentoDetailDTO (Long id, String descripcion, BigDecimal porcentaje, LocalDate fechaInicio, LocalDate fechaFin, List<ProductoShortListDTO> productos) {}
