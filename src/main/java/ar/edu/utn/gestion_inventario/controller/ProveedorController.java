package ar.edu.utn.gestion_inventario.controller;

import ar.edu.utn.gestion_inventario.dto.proveedor.ProveedorDetailDTO;
import ar.edu.utn.gestion_inventario.dto.proveedor.ProveedorListDTO;
import ar.edu.utn.gestion_inventario.dto.proveedor.ProveedorRequestDTO;
import ar.edu.utn.gestion_inventario.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    @PostMapping
    public ResponseEntity<ProveedorDetailDTO> crearProveedor(@Valid @RequestBody ProveedorRequestDTO dto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorService.crearProveedor(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDetailDTO> modificarProveedor(@PathVariable Long id, @Valid @RequestBody ProveedorRequestDTO dto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(proveedorService.modificarProveedor(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<ProveedorListDTO>> listarProveedores()
    {
        return ResponseEntity.status(HttpStatus.OK).body(proveedorService.listarProveedores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDetailDTO> visualizarProveedorPorId(@PathVariable Long id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(proveedorService.visualizarProveedorPorId(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ProveedorDetailDTO> visualizarProveedorPorEmail(@PathVariable String email)
    {
        return ResponseEntity.status(HttpStatus.OK).body(proveedorService.visualizarProveedorPorEmail(email));
    }

    @DeleteMapping("/{id}")
    public void eliminarProveedorPorId(@PathVariable Long id)
    {
        proveedorService.eliminarProveedorPorId(id);
    }
}
