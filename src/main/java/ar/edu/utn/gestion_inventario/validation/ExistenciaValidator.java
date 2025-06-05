package ar.edu.utn.gestion_inventario.validation;

import ar.edu.utn.gestion_inventario.exception.NotFoundException;
import ar.edu.utn.gestion_inventario.model.Existencia;
import ar.edu.utn.gestion_inventario.repository.ExistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExistenciaValidator {
    @Autowired
    ExistenciaRepository existenciaRepository;

    public void verificarListaVacia(List<Existencia> lista)
    {
        if(lista.isEmpty()){
            throw new NotFoundException("No se encontraron descuentos");
        }
    }
    public void verificarSiExisteID(Long id)
    {
        if(!(existenciaRepository.existsById(id))){
            throw  new NotFoundException("El id no existe");
        }
    }
}
