package ar.edu.utn.gestion_inventario.service;

import ar.edu.utn.gestion_inventario.dto.producto.ProductoDetailDTO;
import ar.edu.utn.gestion_inventario.dto.producto.ProductoListDTO;
import ar.edu.utn.gestion_inventario.dto.producto.ProductoRequestDTO;
import ar.edu.utn.gestion_inventario.dto.producto.ProductoRequestPatchDTO;
import ar.edu.utn.gestion_inventario.exception.NotFoundException;
import ar.edu.utn.gestion_inventario.model.Descuento;
import ar.edu.utn.gestion_inventario.model.Producto;
import ar.edu.utn.gestion_inventario.model.Proveedor;
import ar.edu.utn.gestion_inventario.repository.DescuentoRepository;
import ar.edu.utn.gestion_inventario.repository.ProductoRepository;
import ar.edu.utn.gestion_inventario.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static ar.edu.utn.gestion_inventario.validation.ProductoValidator.*;
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
        verificarSiYAExisteCodigoDeBarras(dto.getCodigoBarras(), productoRepository);
        Proveedor proveedor = proveedorRepository.findByEmail(dto.getEmailProveedor()).orElseThrow(() -> new NotFoundException("El email ingresado no existe"));
        if(dto.getIdDescuento() != null)
        {
            Descuento descuento = descuentoRepository.getReferenceById(dto.getIdDescuento());
            Producto producto = productoRepository.save(new Producto(dto.getNombre(), dto.getDescripcion(), dto.getCategoria(), dto.getPrecioUnitario(), dto.getCodigoBarras(), proveedor, descuento));
            return new ProductoDetailDTO(producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getCategoria(), producto.getPrecioUnitario(), producto.getCodigoBarras());
        }
        else
        {
            Producto producto = productoRepository.save(new Producto(dto.getNombre(), dto.getDescripcion(), dto.getCategoria(), dto.getPrecioUnitario(), dto.getCodigoBarras(), proveedor));
            return new ProductoDetailDTO(producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getCategoria(), producto.getPrecioUnitario(), producto.getCodigoBarras());
        }
    }
    public ProductoDetailDTO modificarPrecioPorID(Long id, ProductoRequestPatchDTO dto)
    {
        return productoRepository.findById(id).map(producto -> {
            producto.setPrecioUnitario(dto.getPrecioUnitario());
            producto = productoRepository.save(producto);

            return new ProductoDetailDTO(producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getCategoria(), producto.getPrecioUnitario(), producto.getCodigoBarras());
        }).orElseThrow(() -> new NotFoundException("El ID ingresado no existe"));
    }

    public List<ProductoListDTO> listarProductos()
    {
        List<Producto> lista = productoRepository.findAll();
        comprobarListaVacia(lista);
        return lista.stream().map(producto -> new ProductoListDTO(producto.getId(), producto.getNombre(),
            producto.getCategoria(), producto.getPrecioUnitario())).toList();
    }

    public void eliminarProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        Descuento descuento = producto.getDescuento();
        if (descuento != null) {
            descuento.getProductos().remove(producto);
        }
        productoRepository.delete(producto);
    }

    public List<ProductoListDTO> buscarPorProveedor(String email)
    {
        List<Producto> lista = productoRepository.findAllByProveedorEmail(email);
        comprobarListaVacia(lista);
        return lista.stream().map(producto ->
                new ProductoListDTO(producto.getId(), producto.getNombre(), producto.getCategoria(), producto.getPrecioUnitario())).toList();
    }

    public List<ProductoListDTO> buscarPorCategoria(String categoria)
    {
        List<Producto> lista = productoRepository.findAllByCategoria(categoria);
        comprobarListaVacia(lista);
        return lista.stream().map(producto ->
                new ProductoListDTO(producto.getId(), producto.getNombre(), producto.getCategoria(), producto.getPrecioUnitario())).toList();
    }

    public ProductoDetailDTO visualizarProductoPorId(Long id){
        return productoRepository.findById(id).map(producto -> new ProductoDetailDTO(producto.getId(),producto.getNombre(),producto.getDescripcion(),producto.getCategoria(),producto.getPrecioUnitario(),producto.getCodigoBarras()))
                .orElseThrow(() -> new NotFoundException("El ID ingresado no existe"));
    }

    public ProductoDetailDTO visualizarProductoPorCodigoDeBarras(String codigoBarras){
        return productoRepository.findByCodigoBarras(codigoBarras).map(producto ->  new ProductoDetailDTO(producto.getId(),producto.getNombre(),producto.getDescripcion(),producto.getCategoria(),producto.getPrecioUnitario(),producto.getCodigoBarras()))
                .orElseThrow(() -> new NotFoundException("El codigo de barras ingresado no existe"));
    }

   @Transactional
    public void eliminarProductoPorCodigoDeBarras(String codigoBarra){
        Producto producto = productoRepository.findByCodigoBarras(codigoBarra)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        Descuento descuento = producto.getDescuento();
        if (descuento != null) {
            descuento.getProductos().remove(producto);
        }
        productoRepository.deleteByCodigoBarras(codigoBarra);
    }
}
