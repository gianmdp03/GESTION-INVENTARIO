package ar.edu.utn.gestion_inventario.service;

import ar.edu.utn.gestion_inventario.dto.existencia.ExistenciaDetailDTO;
import ar.edu.utn.gestion_inventario.dto.existencia.ExistenciaListDTO;
import ar.edu.utn.gestion_inventario.dto.existencia.ExistenciaRequestDTO;
import ar.edu.utn.gestion_inventario.exception.NotFoundException;
import ar.edu.utn.gestion_inventario.model.Existencia;
import ar.edu.utn.gestion_inventario.model.Producto;
import ar.edu.utn.gestion_inventario.repository.ExistenciaRepository;
import ar.edu.utn.gestion_inventario.repository.ProductoRepository;
import ar.edu.utn.gestion_inventario.validation.ExistenciaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;
import static ar.edu.utn.gestion_inventario.validation.ExistenciaValidator.*;
@Service
@Validated
public class ExistenciaService {
    @Autowired
    private ExistenciaRepository existenciaRepository;
    @Autowired
    private ProductoRepository productoRepository;

    public ExistenciaDetailDTO crearExistencia(ExistenciaRequestDTO dto)
    {
        Producto producto = productoRepository.findById(dto.getIdProducto()).orElseThrow(() -> new NotFoundException("El ID de producto ingresado no existe"));
        Existencia existencia = existenciaRepository.save(new Existencia(dto.getCantidad(), dto.getFechaEntrada(), dto.getFechaVencimiento(), producto));
        return new ExistenciaDetailDTO(existencia.getId(), existencia.getCantidad(), existencia.getFechaEntrada(), existencia.getFechaVencimiento(), existencia.getProducto().getNombre());
    }

    public ExistenciaDetailDTO modificarStock(Long id,  int stock)
    {
        return existenciaRepository.findById(id).map(existencia -> {
            existencia.setCantidad(stock);
            existencia = existenciaRepository.save(existencia);
            return new ExistenciaDetailDTO(existencia.getId(), existencia.getCantidad(), existencia.getFechaEntrada(), existencia.getFechaVencimiento(), existencia.getProducto().getNombre());
        }).orElseThrow(() -> new NotFoundException("el id ingresado no corresponde a una existencia"));
    }

    public List<ExistenciaListDTO> listarExistencia()
    {
        verificarListaVacia(existenciaRepository.findAll());
        return existenciaRepository.findAll().stream().map(existencia ->
                new ExistenciaListDTO(existencia.getId(), existencia.getCantidad(), existencia.getFechaVencimiento(), existencia.getProducto().getNombre())).toList();
    }

    public List<ExistenciaListDTO> listarExistenciasConMasCantidad(int cantidad)
    {
        verificarListaVacia(existenciaRepository.findAllByCantidadGreaterThanOrderByCantidadDesc(cantidad));
        return existenciaRepository.findAllByCantidadGreaterThanOrderByCantidadDesc(cantidad).stream().map(existencia -> new ExistenciaListDTO(existencia.getId(),
                existencia.getCantidad(), existencia.getFechaEntrada(), existencia.getProducto().getNombre())).toList();
    }

    public List<ExistenciaListDTO> listarExistenciasConMenosCantidad(int cantidad)
    {
        verificarListaVacia(existenciaRepository.findAllByCantidadLessThanOrderByCantidadAsc(cantidad));
        return existenciaRepository.findAllByCantidadLessThanOrderByCantidadAsc(cantidad).stream().map(existencia ->
                new ExistenciaListDTO(existencia.getId(), existencia.getCantidad(), existencia.getFechaVencimiento(), existencia.getProducto().getNombre())).toList();
    }

    public ExistenciaDetailDTO visualizarExistenciaPorId(Long id)
    {
        return existenciaRepository.findById(id).map(existencia->new ExistenciaDetailDTO(existencia.getId(),existencia.getCantidad(),existencia.getFechaEntrada(),existencia.getFechaVencimiento(),existencia.getProducto().getNombre())).orElseThrow(() -> new NotFoundException("El id ingresado no existe"));
    }

    public void eliminarExistencia(Long id)
    {
        verificarSiExisteID(id, existenciaRepository);
        existenciaRepository.deleteById(id);
    }
}
