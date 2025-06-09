package ar.edu.utn.gestion_inventario.dto.producto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class ProductoRequestPatchDTO {
    @PositiveOrZero(message = "El precio del producto no puede ser negativo")
    @NotNull(message = "El precio del producto no puede ser negativo")
    private BigDecimal precio;

    public ProductoRequestPatchDTO(BigDecimal precio) {
        this.precio = precio;
    }

    public ProductoRequestPatchDTO() {
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
