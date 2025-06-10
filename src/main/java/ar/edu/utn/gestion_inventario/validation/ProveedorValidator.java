package ar.edu.utn.gestion_inventario.validation;

import ar.edu.utn.gestion_inventario.exception.NotFoundException;
import ar.edu.utn.gestion_inventario.model.Proveedor;
import ar.edu.utn.gestion_inventario.repository.ProveedorRepository;

import java.util.List;

public class ProveedorValidator {

    public static void verificarSiExisteID(Long id, ProveedorRepository proveedorRepository){
        if(!(proveedorRepository.existsById(id))){
            throw  new NotFoundException("El id no existe");
        }
    }

    public static void comprobarListaVacia(List<Proveedor> lista)
    {
        if(lista.isEmpty())
        {
            throw new NotFoundException("No se encontraron proveedores");
        }
    }

    public static void verificarSiYAExisteEmail(String email, ProveedorRepository proveedorRepository){
        if(proveedorRepository.existsByEmail(email)){
            throw  new NotFoundException("El email ya existe");
        }
    }
    public static void verificarSiExisteEmail(String email, ProveedorRepository proveedorRepository){
        if(!(proveedorRepository.existsByEmail(email))){
            throw  new NotFoundException("El email no existe");
        }
    }
}
