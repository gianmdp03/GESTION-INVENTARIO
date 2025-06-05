package ar.edu.utn.gestion_inventario.service;

import ar.edu.utn.gestion_inventario.dto.existencia.ExistenciaDetailDTO;
import ar.edu.utn.gestion_inventario.dto.existencia.ExistenciaListDTO;
import ar.edu.utn.gestion_inventario.dto.existencia.ExistenciaRequestDTO;
import ar.edu.utn.gestion_inventario.exception.NotFoundException;
import ar.edu.utn.gestion_inventario.model.Existencia;
import ar.edu.utn.gestion_inventario.model.Producto;
import ar.edu.utn.gestion_inventario.repository.ExistenciaRepository;
import ar.edu.utn.gestion_inventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
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

    public void eliminarExistencia(Long id){
        existenciaRepository.deleteById(id);
    }

    public ExistenciaDetailDTO modificarStock(Long id,  int stock){
        return existenciaRepository.findById(id).map(existencia -> {
            existencia.setCantidad(stock);
            existencia = existenciaRepository.save(existencia);
            return new ExistenciaDetailDTO(existencia.getId(), existencia.getCantidad(), existencia.getFechaEntrada(), existencia.getFechaVencimiento(), existencia.getProducto().getNombre());
        }).orElseThrow(() -> new NotFoundException("el id ingresado no corresponde a una existencia"));
    }
    public List<ExistenciaListDTO> mostrarExistenciasPorCantidad(int cantidad)
    {
        return existenciaRepository.findAllByCantidadLessThanOrderByCantidadAsc(cantidad).stream().map(existencia -> new ExistenciaListDTO(
                existencia.getId(), existencia.getCantidad(), existencia.getFechaVencimiento(), existencia.getProducto().getNombre())).toList();
    }
}
