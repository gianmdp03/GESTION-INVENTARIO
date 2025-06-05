package ar.edu.utn.gestion_inventario.service;

import ar.edu.utn.gestion_inventario.dto.proveedor.ProveedorDetailDTO;
import ar.edu.utn.gestion_inventario.dto.proveedor.ProveedorListDTO;
import ar.edu.utn.gestion_inventario.dto.proveedor.ProveedorRequestDTO;
import ar.edu.utn.gestion_inventario.exception.NotFoundException;
import ar.edu.utn.gestion_inventario.model.Proveedor;
import ar.edu.utn.gestion_inventario.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;

    public ProveedorDetailDTO crearProveedor(ProveedorRequestDTO dto)
    {
        Proveedor proveedor = proveedorRepository.save(new Proveedor(dto.getNombre(), dto.getTelefono(), dto.getEmail(), dto.getDireccion()));
        return new ProveedorDetailDTO(proveedor.getId(), proveedor.getNombre(), proveedor.getTelefono(), proveedor.getEmail(), proveedor.getEmail());
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
        List<ProveedorListDTO> dto = proveedorRepository.findAll().stream().map(proveedor -> new ProveedorListDTO(proveedor.getId(),
                proveedor.getNombre(), proveedor.getTelefono(), proveedor.getEmail())).toList();
        return dto;
    }
    public ProveedorDetailDTO visualizarProveedorPorId(Long id){
        return proveedorRepository.findById(id).map(proveedor -> new ProveedorDetailDTO(proveedor.getId(),proveedor.getNombre(),proveedor.getTelefono(),proveedor.getEmail(),proveedor.getDireccion())).orElseThrow();
    }

    public ProveedorDetailDTO visualizarProveedorPorEmail(String email){
        return proveedorRepository.findByEmail(email).map(proveedor -> new ProveedorDetailDTO(proveedor.getId(),proveedor.getNombre(),proveedor.getTelefono(),proveedor.getEmail(),proveedor.getDireccion())).orElseThrow();
    }

}