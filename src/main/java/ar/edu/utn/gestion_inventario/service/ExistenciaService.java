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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
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
        List<Existencia> lista = existenciaRepository.findAll();
        verificarListaVacia(lista);
        return lista.stream().map(existencia ->
                new ExistenciaListDTO(existencia.getId(), existencia.getCantidad(), existencia.getFechaVencimiento(), existencia.getProducto().getNombre())).toList();
    }

    public List<ExistenciaListDTO> listarExistenciasConMasCantidad(Long cantidad)
    {
        Long total = existenciaRepository.count();
        if(cantidad>total)
        {
            cantidad = total;
        }
        List<Existencia> lista = existenciaRepository.findAllByCantidadGreaterThanOrderByCantidadDesc(cantidad);
        verificarListaVacia(lista);
        return lista.stream().map(existencia -> new ExistenciaListDTO(existencia.getId(),
                existencia.getCantidad(), existencia.getFechaEntrada(), existencia.getProducto().getNombre())).toList();
    }

    public List<ExistenciaListDTO> listarExistenciasConMenosCantidad(Long cantidad)
    {
        Long total = existenciaRepository.count();
        if(cantidad<total)
        {
            cantidad = total;
        }
        List<Existencia> lista = existenciaRepository.findAllByCantidadLessThanOrderByCantidadAsc(cantidad);
        verificarListaVacia(lista);
        return lista.stream().map(existencia ->
                new ExistenciaListDTO(existencia.getId(), existencia.getCantidad(), existencia.getFechaVencimiento(), existencia.getProducto().getNombre())).toList();
    }

    public ExistenciaDetailDTO visualizarExistenciaPorId(Long id)
    {
        return existenciaRepository.findById(id).map(existencia->new ExistenciaDetailDTO(existencia.getId(),existencia.getCantidad(),existencia.getFechaEntrada(),existencia.getFechaVencimiento(),existencia.getProducto().getNombre())).orElseThrow(() -> new NotFoundException("El id ingresado no existe"));
    }

    @Transactional
    public void eliminarExistenciasVencidas()
    {
        existenciaRepository.deleteByFechaVencimientoBefore(LocalDate.now());
    }

    public void eliminarExistencia(Long id)
    {
        verificarSiExisteID(id, existenciaRepository);
        existenciaRepository.deleteById(id);
    }
}
