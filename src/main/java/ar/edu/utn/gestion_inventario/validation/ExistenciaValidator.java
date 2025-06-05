package ar.edu.utn.gestion_inventario.validation;

import ar.edu.utn.gestion_inventario.exception.NotFoundException;
import ar.edu.utn.gestion_inventario.model.Descuento;

import java.util.List;

public class ExistenciaValidator {
    public void verificarListaVacia(List<Descuento> lista)
    {
        if(lista.isEmpty()){
            throw new NotFoundException("No se encontraron descuentos");
        }
    }
}
