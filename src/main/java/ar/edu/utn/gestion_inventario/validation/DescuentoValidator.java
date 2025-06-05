package ar.edu.utn.gestion_inventario.validation;

import ar.edu.utn.gestion_inventario.exception.NotFoundException;
import ar.edu.utn.gestion_inventario.model.Descuento;
import ar.edu.utn.gestion_inventario.repository.DescuentoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DescuentoValidator {
    @Autowired
    private DescuentoRepository descuentoRepository;

    public void verificarListaVacia(List<Descuento> lista){
        if(lista.isEmpty()){
            throw new NotFoundException("No se encontraron descuentos");
        }
    }
}
