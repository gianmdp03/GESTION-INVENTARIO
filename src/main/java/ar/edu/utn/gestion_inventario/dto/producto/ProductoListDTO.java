package ar.edu.utn.gestion_inventario.dto.producto;

import java.math.BigDecimal;

public record ProductoListDTO (Long id, String nombre, String categoria, BigDecimal precio){}