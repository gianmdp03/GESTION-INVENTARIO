package ar.edu.utn.gestion_inventario.dto.existencia;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ExistenciaRequestPutDTO {
    @NotNull(message= "La cantidad no puede estar vacía")
    @Positive(message= "La cantidad no puede ser negativa o igual a 0")
    private int cantidad;

    public ExistenciaRequestPutDTO() {
    }

    public ExistenciaRequestPutDTO(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
