package ar.edu.utn.gestion_inventario.dto.producto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ProductoRequestPatchDTO {
    @NotNull
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
