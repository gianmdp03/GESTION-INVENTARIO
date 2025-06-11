package ar.edu.utn.gestion_inventario.service;

import ar.edu.utn.gestion_inventario.dto.proveedor.ProveedorDetailDTO;
import ar.edu.utn.gestion_inventario.dto.proveedor.ProveedorListDTO;
import ar.edu.utn.gestion_inventario.dto.proveedor.ProveedorRequestDTO;
import ar.edu.utn.gestion_inventario.exception.NotFoundException;
import ar.edu.utn.gestion_inventario.model.Producto;
import ar.edu.utn.gestion_inventario.model.Proveedor;
import ar.edu.utn.gestion_inventario.repository.ProductoRepository;
import ar.edu.utn.gestion_inventario.repository.ProveedorRepository;
import ar.edu.utn.gestion_inventario.validation.ProveedorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

import static ar.edu.utn.gestion_inventario.validation.ProveedorValidator.*;
@Service
@Validated
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;
    private ProductoRepository productoRepository;
    public ProveedorDetailDTO crearProveedor(ProveedorRequestDTO dto)
    {
        verificarSiYAExisteEmail(dto.getEmail(), proveedorRepository);
        Proveedor proveedor = proveedorRepository.save(new Proveedor(dto.getNombre(), dto.getTelefono(), dto.getEmail(), dto.getDireccion()));
        return new ProveedorDetailDTO(proveedor.getId(), proveedor.getNombre(), proveedor.getTelefono(), proveedor.getEmail(), proveedor.getDireccion());
    }

    public ProveedorDetailDTO modificarProveedor(Long id, ProveedorRequestDTO dto)
    {
        return proveedorRepository.findById(id).map(proveedor -> {
            proveedor.setNombre(dto.getNombre());
            proveedor.setDireccion(dto.getDireccion());
            proveedor.setTelefono(dto.getTelefono());
            proveedor = proveedorRepository.save(proveedor);

            return new ProveedorDetailDTO(proveedor.getId(), proveedor.getNombre(), proveedor.getTelefono(), proveedor.getEmail(), proveedor.getDireccion());
        }).orElseThrow(() -> new NotFoundException("El ID ingresado no corresponse a un proveedor"));
    }

    public List<ProveedorListDTO> listarProveedores()
    {
        List<Proveedor> lista = proveedorRepository.findAll();
        comprobarListaVacia(lista);
        return lista.stream().map(proveedor -> new ProveedorListDTO(proveedor.getId(),
                proveedor.getNombre(), proveedor.getTelefono(), proveedor.getEmail())).toList();
    }

    public ProveedorDetailDTO visualizarProveedorPorId(Long id){
        return proveedorRepository.findById(id).map(proveedor -> new ProveedorDetailDTO(proveedor.getId(),proveedor.getNombre(),proveedor.getTelefono(),proveedor.getEmail(),proveedor.getDireccion()))
                .orElseThrow(() -> new NotFoundException("El ID ingresado no existe"));
    }

    public ProveedorDetailDTO visualizarProveedorPorEmail(String email){
        return proveedorRepository.findByEmail(email).map(proveedor -> new ProveedorDetailDTO(proveedor.getId(),proveedor.getNombre(),proveedor.getTelefono(),proveedor.getEmail(),proveedor.getDireccion()))
                .orElseThrow(() -> new NotFoundException("El email ingresado no existe"));
    }

    @Transactional
    public void eliminarProveedorPorId(Long id) {

        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proveedor no encontrado con id: " + id));

        List<Producto> productos = productoRepository.findByProveedor(proveedor);

        for (Producto producto : productos) {
            producto.setProveedor(null);
        }
        productoRepository.saveAll(productos);

        proveedorRepository.deleteById(id);
    }

    @Transactional
    public void eliminarProveedorPorEmail(String email){
        verificarSiExisteEmail(email,proveedorRepository);
        proveedorRepository.deleteByEmail(email);
    }
    public ProveedorDetailDTO modificarProveedorPorEmail(String email, ProveedorRequestDTO proveedorRequestDTO){
        return proveedorRepository.findByEmail(email).map(proveedor -> {
            proveedor.setDireccion(proveedorRequestDTO.getDireccion());
            proveedor.setNombre(proveedorRequestDTO.getNombre());
            proveedor.setTelefono(proveedorRequestDTO.getTelefono());
            proveedor=proveedorRepository.save(proveedor);

            return new ProveedorDetailDTO(proveedor.getId(),proveedor.getNombre(),proveedor.getTelefono(),proveedor.getEmail(),proveedor.getDireccion());
        }).orElseThrow(()->new NotFoundException("EL email ingresado no pertenece a un proveedor"));
    }
}