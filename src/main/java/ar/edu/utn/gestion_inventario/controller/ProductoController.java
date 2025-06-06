package ar.edu.utn.gestion_inventario.controller;

import ar.edu.utn.gestion_inventario.dto.producto.ProductoDetailDTO;
import ar.edu.utn.gestion_inventario.dto.producto.ProductoRequestDTO;
import ar.edu.utn.gestion_inventario.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoDetailDTO> crearProducto(@Valid @RequestBody ProductoRequestDTO dto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.crearProducto(dto));
    }
}
