package ar.edu.utn.gestion_inventario.dto.producto;

import java.math.BigDecimal;

public record ProductoDetailDTO (Long id, String nombre, String descripcion, String categoria, BigDecimal precio, String codigoBarras) {}
