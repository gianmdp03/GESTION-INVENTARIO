package ar.edu.utn.gestion_inventario.validation;

import ar.edu.utn.gestion_inventario.exception.BadRequestException;
import ar.edu.utn.gestion_inventario.exception.NotFoundException;
import ar.edu.utn.gestion_inventario.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProveedorValidator {
    @Autowired
    private ProveedorRepository proveedorRepositorio;

    public void verificarSiExisteEmail(String email){
        if(!(proveedorRepositorio.existsByEmail(email))){
            throw  new NotFoundException("El email no existe");
        }
    }

    public void verificarSiExisteID(Long id){
        if(!(proveedorRepositorio.existsById(id))){
            throw  new NotFoundException("El id no existe");
        }
    }

    public void verificarSiYAExisteID(Long id){
        if((proveedorRepositorio.existsById(id))){
            throw  new BadRequestException("El id ya existe");
        }
    }

    public void verificarSiYAExisteEmail(String email){
        if((proveedorRepositorio.existsByEmail(email))){
            throw  new NotFoundException("El email ya existe");
        }
    }
}
