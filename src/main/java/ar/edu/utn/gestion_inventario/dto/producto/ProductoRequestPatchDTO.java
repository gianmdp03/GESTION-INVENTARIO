package ar.edu.utn.gestion_inventario.dto.producto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ProductoRequestPatchDTO {
    @NotNull
    private BigDecimal precioUnitario;

    public ProductoRequestPatchDTO(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public ProductoRequestPatchDTO() {
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
