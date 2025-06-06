package ar.edu.utn.gestion_inventario.dto.producto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ProductoRequestPutDTO {
    @NotNull
    private BigDecimal precioUnitario;

    public ProductoRequestPutDTO(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public ProductoRequestPutDTO() {
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
