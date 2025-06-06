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
import ar.edu.utn.gestion_inventario.validation.DescuentoValidator;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class DescuentoService {
    @Autowired
    private DescuentoRepository descuentoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DescuentoValidator descuentoValidator;

    public DescuentoDetailDTO crearDescuento(DescuentoRequestDTO dto)
    {
        List<ProductoShortListDTO> productos = new ArrayList<>();
        List<Producto> listaAuxiliar = new ArrayList<>();
        Descuento descuento = new Descuento(dto.getDescripcion(), dto.getPorcentaje(), dto.getFechaInicio(), dto.getFechaFin());
        if(dto.getProductos() != null && !(dto.getProductos().isEmpty()))
        {
            listaAuxiliar = productoRepository.findAllById(dto.getProductos());
            descuento.setProductos(listaAuxiliar);
            productos = listaAuxiliar.stream().map(producto -> new ProductoShortListDTO(producto.getId(), producto.getNombre())).toList();
        }
        descuento = descuentoRepository.save(descuento);
        return new DescuentoDetailDTO(descuento.getId(), descuento.getDescripcion(), descuento.getPorcentaje(), descuento.getFechaInicio(), descuento.getFechaFin(), productos);
    }

    public DescuentoDetailDTO modificarDescuento(Long id, DescuentoRequestDTO dto)
    {
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

    public List<DescuentoListDTO> listarDescuentos()
    {
        descuentoValidator.verificarListaVacia(descuentoRepository.findAll());
        return descuentoRepository.findAll().stream().map(descuento ->
                new DescuentoListDTO(descuento.getId(), descuento.getPorcentaje(), descuento.getFechaInicio(), descuento.getFechaFin())).toList();
    }

    public List<DescuentoListDTO> filtrarPorFechaInicioASC(){
        descuentoValidator.verificarListaVacia(descuentoRepository.findAll());
        return descuentoRepository.findAllByOrderByFechaInicioAsc().stream().map(descuento -> new DescuentoListDTO(descuento.getId(),descuento.getPorcentaje(),descuento.getFechaInicio(),descuento.getFechaFin())).toList();
    }

    public DescuentoDetailDTO visualizarDescuentoPorId(Long id){
        List<ProductoShortListDTO> lista;
        Descuento descuento = descuentoRepository.getReferenceById(id);
        lista = descuento.getProductos().stream().map(producto->new ProductoShortListDTO(producto.getId(),producto.getNombre())).toList();
        return descuentoRepository.findById(id).map(desc -> new DescuentoDetailDTO(desc.getId(),desc.getDescripcion(),desc.getPorcentaje(),desc.getFechaInicio(),desc.getFechaFin(),lista)).orElseThrow(
                ()->new NotFoundException("El id ingresado no existe"));
    }

    @Transactional
    public void eliminarDescuentosExpirados()
    {
        List<Descuento> expirados = descuentoRepository.findAllByFechaFinBefore(LocalDate.now());
        for(Descuento d : expirados)
        {
            for(Producto p : d.getProductos())
            {
                p.setDescuento(null);
            }
        }
        List<Producto> productosActualizados = expirados.stream().flatMap(descuento ->
                descuento.getProductos().stream()).toList();

        productoRepository.saveAll(productosActualizados);
        descuentoRepository.deleteAll(expirados);
    }
}
