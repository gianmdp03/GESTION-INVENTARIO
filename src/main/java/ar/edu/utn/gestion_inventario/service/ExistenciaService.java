package ar.edu.utn.gestion_inventario.service;

import ar.edu.utn.gestion_inventario.dto.descuento.DescuentoDetailDTO;
import ar.edu.utn.gestion_inventario.dto.descuento.DescuentoListDTO;
import ar.edu.utn.gestion_inventario.dto.descuento.DescuentoRequestDTO;
import ar.edu.utn.gestion_inventario.dto.existencia.ExistenciaDetailDTO;
import ar.edu.utn.gestion_inventario.dto.existencia.ExistenciaListDTO;
import ar.edu.utn.gestion_inventario.dto.existencia.ExistenciaRequestDTO;
import ar.edu.utn.gestion_inventario.dto.producto.ProductoShortListDTO;
import ar.edu.utn.gestion_inventario.model.Descuento;
import ar.edu.utn.gestion_inventario.model.Existencia;
import ar.edu.utn.gestion_inventario.model.Producto;
import ar.edu.utn.gestion_inventario.repository.ExistenciaRepository;
import ar.edu.utn.gestion_inventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class ExistenciaService {
    @Autowired
    private ExistenciaRepository existenciaRepository;
    @Autowired
    private ProductoRepository productoRepository;

    public ExistenciaDetailDTO crearExistencia(ExistenciaRequestDTO dto)
    {
        Producto producto = productoRepository.getReferenceById(dto.getIdProducto());
        Existencia existencia = existenciaRepository.save(new Existencia(dto.getCantidad(), dto.getFechaEntrada(), dto.getFechaVencimiento(), producto));
        return new ExistenciaDetailDTO(existencia.getId(), existencia.getCantidad(), existencia.getFechaEntrada(), existencia.getFechaVencimiento(), existencia.getProducto().getNombre());
    }

    public List<ExistenciaListDTO> listarExistencia(){
        return existenciaRepository.findAll().stream().map(existencia ->
                new ExistenciaListDTO(existencia.getId(), existencia.getCantidad(), existencia.getFechaVencimiento(), existencia.getProducto().getNombre())).toList();
    }
}
