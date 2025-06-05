package ar.edu.utn.gestion_inventario.validation;

import ar.edu.utn.gestion_inventario.exception.BadRequestException;
import ar.edu.utn.gestion_inventario.exception.NotFoundException;
import ar.edu.utn.gestion_inventario.model.Producto;
import ar.edu.utn.gestion_inventario.repository.ProductoRepository;
import ar.edu.utn.gestion_inventario.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductoValidator {
    @Autowired
    private ProductoRepository productoRepository;

    public void comprobarListaVacia(List<Producto> lista)
    {
        if(lista.isEmpty())
        {
            throw new NotFoundException("No se encontraron productos");
        }
    }

    public void verificarSiYAExisteCodigoDeBarras(String codigoBarras){
        if((productoRepository.existsByCodigoBarras(codigoBarras))){
            throw new NotFoundException("El codigo de barras no existe");
        }
    }
    
}
