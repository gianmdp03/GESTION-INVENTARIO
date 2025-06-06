package ar.edu.utn.gestion_inventario.controller;

import ar.edu.utn.gestion_inventario.dto.producto.ProductoDetailDTO;
import ar.edu.utn.gestion_inventario.dto.producto.ProductoListDTO;
import ar.edu.utn.gestion_inventario.dto.producto.ProductoRequestDTO;
import ar.edu.utn.gestion_inventario.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoDetailDTO> crearProducto(@Valid @RequestBody ProductoRequestDTO dto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.crearProducto(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDetailDTO> modificarProducto(@PathVariable Long id, @Valid @RequestBody ProductoRequestDTO dto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.modificarPrecio(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<ProductoListDTO>> listarProductos()
    {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.listarProductos());
    }

    @DeleteMapping("/{id}")
    public void eliminarProductoPorId(@PathVariable Long id)
    {
        productoService.eliminarProducto(id);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<ProductoListDTO>> buscarPorProveedor(@PathVariable String email)
    {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.buscarPorProveedor(email));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProductoListDTO>> buscarPorCategoria(@PathVariable String categoria)
    {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.buscarPorCategoria(categoria));
    }
}
