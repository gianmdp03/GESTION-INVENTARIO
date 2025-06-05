package ar.edu.utn.gestion_inventario.validation;

import ar.edu.utn.gestion_inventario.exception.BadRequestException;
import ar.edu.utn.gestion_inventario.exception.NotFoundException;
import ar.edu.utn.gestion_inventario.model.Proveedor;
import ar.edu.utn.gestion_inventario.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProveedorValidator {
    @Autowired
    private ProveedorRepository proveedorRepository;

    public void verificarSiExisteID(Long id){
        if(!(proveedorRepository.existsById(id))){
            throw  new NotFoundException("El id no existe");
        }
    }
    public void comprobarListaVacia(List<Proveedor> lista)
    {
        if(lista.isEmpty())
        {
            throw new NotFoundException("No se encontraron proveedores");
        }
    }
    public void verificarSiYAExisteEmail(String email){
        if(proveedorRepository.existsByEmail(email)){
            throw  new NotFoundException("El email ya existe");
        }
    }
}
