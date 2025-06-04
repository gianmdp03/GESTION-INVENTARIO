package ar.edu.utn.gestion_inventario.dto.descuento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DescuentoListDTO (Long id, BigDecimal porcentaje, LocalDate fechaInicio, LocalDate fechaFin){}
