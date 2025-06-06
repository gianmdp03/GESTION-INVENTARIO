package ar.edu.utn.gestion_inventario.validation;

import ar.edu.utn.gestion_inventario.exception.NotFoundException;
import ar.edu.utn.gestion_inventario.model.Existencia;
import ar.edu.utn.gestion_inventario.repository.ExistenciaRepository;

import java.util.List;

public class ExistenciaValidator {

    public static void verificarListaVacia(List<Existencia> lista)
    {
        if(lista.isEmpty()){
            throw new NotFoundException("No se encontraron descuentos");
        }
    }
    public static void verificarSiExisteID(Long id, ExistenciaRepository existenciaRepository)
    {
        if(!(existenciaRepository.existsById(id))){
            throw  new NotFoundException("El id no existe");
        }
    }
}
