package ar.edu.utn.gestion_inventario.validation;

import ar.edu.utn.gestion_inventario.exception.NotFoundException;
import ar.edu.utn.gestion_inventario.model.Producto;
import ar.edu.utn.gestion_inventario.repository.ProductoRepository;

import java.util.List;

public class ProductoValidator {

    public static void comprobarListaVacia(List<Producto> lista)
    {
        if(lista.isEmpty())
        {
            throw new NotFoundException("No se encontraron productos");
        }
    }

    public static void verificarSiYAExisteCodigoDeBarras(String codigoBarras, ProductoRepository productoRepository){
        if((productoRepository.existsByCodigoBarras(codigoBarras))){
            throw new NotFoundException("El codigo de barras no existe");
        }
    }
    
}
