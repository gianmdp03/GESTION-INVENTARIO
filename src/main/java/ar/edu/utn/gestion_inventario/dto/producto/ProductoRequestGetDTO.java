package ar.edu.utn.gestion_inventario.dto.producto;

import jakarta.validation.constraints.NotBlank;

public class ProductoRequestGetDTO {
    @NotBlank(message = "El código de barras del producto es obligatorio")
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
