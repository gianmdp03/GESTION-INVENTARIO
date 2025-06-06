package ar.edu.utn.gestion_inventario.dto.producto;

import jakarta.validation.constraints.NotBlank;

public class ProductoRequestGetDTO {
    @NotBlank
    private String codigoBarras;

    public ProductoRequestGetDTO() {
    }

    public ProductoRequestGetDTO(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }
}
