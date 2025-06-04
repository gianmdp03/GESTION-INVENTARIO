package ar.edu.utn.gestion_inventario.dto.existencia;

import java.time.LocalDate;

public record ExistenciaDetailDTO (Long id, int cantidad, LocalDate fechaEntrada, LocalDate fechaVencimiento, String nombreProducto){}
