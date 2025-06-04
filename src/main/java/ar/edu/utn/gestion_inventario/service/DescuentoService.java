package ar.edu.utn.gestion_inventario.service;

import ar.edu.utn.gestion_inventario.dto.descuento.DescuentoDetailDTO;
import ar.edu.utn.gestion_inventario.dto.descuento.DescuentoRequestDTO;
import ar.edu.utn.gestion_inventario.dto.producto.ProductoShortListDTO;
import ar.edu.utn.gestion_inventario.model.Descuento;
import ar.edu.utn.gestion_inventario.model.Producto;
import ar.edu.utn.gestion_inventario.repository.DescuentoRepository;
import ar.edu.utn.gestion_inventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class DescuentoService {
    @Autowired
    private DescuentoRepository descuentoRepository;
    @Autowired
    private ProductoRepository productoRepository;
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
}
