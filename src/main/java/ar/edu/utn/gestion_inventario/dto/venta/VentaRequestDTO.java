package ar.edu.utn.gestion_inventario.dto.venta;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class VentaRequestDTO {
    @NotNull
    private BigDecimal total;

    public VentaRequestDTO(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}