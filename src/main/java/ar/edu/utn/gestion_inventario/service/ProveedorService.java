package ar.edu.utn.gestion_inventario.service;

import ar.edu.utn.gestion_inventario.dto.proveedor.ProveedorDetailDTO;
import ar.edu.utn.gestion_inventario.dto.proveedor.ProveedorListDTO;
import ar.edu.utn.gestion_inventario.dto.proveedor.ProveedorRequestDTO;
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
    public List<ProveedorListDTO> listarProveedores()
    {
        List<ProveedorListDTO> dto = proveedorRepository.findAll().stream().map(proveedor -> new ProveedorListDTO(proveedor.getId(),
                proveedor.getNombre(), proveedor.getTelefono(), proveedor.getEmail())).toList();
        return dto;
    }
}