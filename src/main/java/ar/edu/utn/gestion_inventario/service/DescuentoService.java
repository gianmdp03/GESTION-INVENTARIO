package ar.edu.utn.gestion_inventario.service;

import ar.edu.utn.gestion_inventario.dto.descuento.DescuentoDetailDTO;
import ar.edu.utn.gestion_inventario.dto.descuento.DescuentoListDTO;
import ar.edu.utn.gestion_inventario.dto.descuento.DescuentoRequestDTO;
import ar.edu.utn.gestion_inventario.dto.producto.ProductoShortListDTO;
import ar.edu.utn.gestion_inventario.exception.NotFoundException;
import ar.edu.utn.gestion_inventario.model.Descuento;
import ar.edu.utn.gestion_inventario.model.Producto;
import ar.edu.utn.gestion_inventario.repository.DescuentoRepository;
import ar.edu.utn.gestion_inventario.repository.ProductoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ar.edu.utn.gestion_inventario.validation.DescuentoValidator.*;
@Service
@Validated
public class DescuentoService {
    @Autowired
    private DescuentoRepository descuentoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public DescuentoDetailDTO crearDescuento(DescuentoRequestDTO dto) {
        List<ProductoShortListDTO> productos = new ArrayList<>();
        Descuento descuento = new Descuento(dto.getDescripcion(), dto.getPorcentaje(), dto.getFechaInicio(), dto.getFechaFin());
        if (dto.getProductos() != null && !(dto.getProductos().isEmpty())) {
            List<Producto> listaAuxiliar = productoRepository.findAllById(dto.getProductos());
            descuento.setProductos(listaAuxiliar);
            productos = listaAuxiliar.stream().map(producto -> new ProductoShortListDTO(producto.getId(), producto.getNombre())).toList();
        }
        descuento = descuentoRepository.save(descuento);
        return new DescuentoDetailDTO(descuento.getId(), descuento.getDescripcion(), descuento.getPorcentaje(), descuento.getFechaInicio(), descuento.getFechaFin(), productos);
    }

    public DescuentoDetailDTO modificarDescuento(Long id, DescuentoRequestDTO dto) {
        return descuentoRepository.findById(id).map(descuento -> {
            descuento.setPorcentaje(dto.getPorcentaje());
            descuento.setFechaInicio(dto.getFechaInicio());
            descuento.setFechaFin(dto.getFechaFin());

            descuento = descuentoRepository.save(descuento);

            List<ProductoShortListDTO> productosDTO = descuento.getProductos().stream()
                    .map(p -> new ProductoShortListDTO(p.getId(), p.getNombre()))
                    .toList();

            return new DescuentoDetailDTO(descuento.getId(), descuento.getDescripcion(), descuento.getPorcentaje(), descuento.getFechaInicio(),
                    descuento.getFechaFin(), productosDTO);
        }).orElseThrow(() -> new NotFoundException("Descuento no encontrado"));
    }

    public List<DescuentoListDTO> listarDescuentos() {
        List<Descuento> lista = descuentoRepository.findAll();
        verificarListaVacia(lista);
        return lista.stream().map(descuento ->
                new DescuentoListDTO(descuento.getId(), descuento.getPorcentaje(), descuento.getFechaInicio(), descuento.getFechaFin())).toList();
    }

    public List<DescuentoListDTO> filtrarPorFechaInicioASC() {
        List<Descuento> lista = descuentoRepository.findAllByOrderByFechaInicioAsc();
        verificarListaVacia(lista);
        return lista.stream().map(descuento -> new DescuentoListDTO(descuento.getId(), descuento.getPorcentaje(), descuento.getFechaInicio(), descuento.getFechaFin())).toList();
    }

    public DescuentoDetailDTO visualizarDescuentoPorId(Long id) {
        Descuento descuento = descuentoRepository.findById(id).orElseThrow(() -> new NotFoundException("El ID ingresado no existe"));
        List<ProductoShortListDTO> lista = descuento.getProductos().stream().map(producto -> new ProductoShortListDTO(producto.getId(), producto.getNombre())).toList();
        return descuentoRepository.findById(id).map(desc -> new DescuentoDetailDTO(desc.getId(), desc.getDescripcion(), desc.getPorcentaje(), desc.getFechaInicio(), desc.getFechaFin(), lista))
                .orElseThrow(() -> new NotFoundException("El id ingresado no existe"));
    }

    @Transactional
    public void eliminarDescuentosExpirados() {
        List<Descuento> expirados = descuentoRepository.findAllByFechaFinBefore(LocalDate.now());
        for (Descuento d : expirados) {
            for (Producto p : d.getProductos()) {
                p.setDescuento(null);
            }
        }
        List<Producto> productosActualizados = expirados.stream().flatMap(descuento ->
                descuento.getProductos().stream()).toList();

        productoRepository.saveAll(productosActualizados);
        descuentoRepository.deleteAll(expirados);
    }

    public void eliminarDescuentoPorId(Long id) {
        Optional<Descuento> descuentoOptional = descuentoRepository.findById(id);
        if (descuentoOptional.isPresent()) {
            Descuento descuento = descuentoOptional.get();
            for (Producto producto : descuento.getProductos()) {
                producto.setDescuento(null);
            }

            List<Producto> productosActualizados = descuento.getProductos();
            productoRepository.saveAll(productosActualizados);
            descuentoRepository.deleteById(id);
        }else{
            throw new NotFoundException("No se encontro el descuento con el Id brindado");
        }
    }
}