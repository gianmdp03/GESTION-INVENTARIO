package ar.edu.utn.gestion_inventario.service;

import ar.edu.utn.gestion_inventario.dto.producto.ProductoDetailDTO;
import ar.edu.utn.gestion_inventario.dto.producto.ProductoListDTO;
import ar.edu.utn.gestion_inventario.dto.producto.ProductoRequestDTO;
import ar.edu.utn.gestion_inventario.model.Descuento;
import ar.edu.utn.gestion_inventario.model.Producto;
import ar.edu.utn.gestion_inventario.model.Proveedor;
import ar.edu.utn.gestion_inventario.repository.DescuentoRepository;
import ar.edu.utn.gestion_inventario.repository.ProductoRepository;
import ar.edu.utn.gestion_inventario.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DescuentoRepository descuentoRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    public ProductoDetailDTO crearProducto(ProductoRequestDTO dto)
    {
        Descuento descuento = descuentoRepository.getReferenceById(dto.getIdDescuento());
        Proveedor proveedor = proveedorRepository.getReferenceByEmail(dto.getEmailProveedor());
        Producto producto = productoRepository.save(new Producto(dto.getNombre(), dto.getDescripcion(), dto.getCategoria(), dto.getPrecioUnitario(), dto.getCodigoBarras(), proveedor, descuento));
        return new ProductoDetailDTO(producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getCategoria(), producto.getPrecioUnitario(), producto.getCodigoBarras());
    }
    
    public List<ProductoListDTO> listarProductos()
    {
        return productoRepository.findAll().stream().map(producto -> new ProductoListDTO(producto.getId(), producto.getNombre(),
                producto.getCategoria(), producto.getPrecioUnitario())).toList();
    }
}
