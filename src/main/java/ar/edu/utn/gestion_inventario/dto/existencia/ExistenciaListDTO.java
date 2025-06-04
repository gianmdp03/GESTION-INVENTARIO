package ar.edu.utn.gestion_inventario.dto.existencia;

import java.time.LocalDate;

public record ExistenciaListDTO (Long id, int cantidad, LocalDate fechaVencimiento, String nombreProducto){}
