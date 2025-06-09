package ar.edu.utn.gestion_inventario.controller;

import ar.edu.utn.gestion_inventario.dto.producto.*;
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
    public ResponseEntity<ProductoDetailDTO> modificarProductoPorId(@PathVariable Long id, @Valid @RequestBody ProductoRequestPutDTO dto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.modificarProductoPorId(id, dto));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ProductoDetailDTO> modificarPrecioPorId(@PathVariable Long id, @Valid @RequestBody ProductoRequestPatchDTO dto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.modificarPrecioPorId(id, dto));
    }
    @PutMapping("/codigobarras/{codigobarras}")
    public ResponseEntity<ProductoDetailDTO> modificarProductoPorCodigoBarras(@PathVariable String codigoBarras, @Valid @RequestBody ProductoRequestPutDTO dto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.modificarProductoPorCodigoBarras(codigoBarras, dto));
    }
    @PatchMapping("/codigobarras/{codigobarras}")
    public ResponseEntity<ProductoDetailDTO> modificarPrecioPorCodigoBarras(@PathVariable String codigoBarras, @Valid @RequestBody ProductoRequestPatchDTO dto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.modificarPrecioPorCodigoBarras(codigoBarras, dto));
    }
    @GetMapping
    public ResponseEntity<List<ProductoListDTO>> listarProductos()
    {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.listarProductos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProductoPorId(@PathVariable Long id)
    {
        productoService.eliminarProducto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDetailDTO> visualizarProductoPorId(@PathVariable Long id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.visualizarProductoPorId(id));
    }

    @GetMapping("/codigobarras/{codigobarras}")
    public ResponseEntity<ProductoDetailDTO> visualizarProductoPorCodigoDeBarras(@PathVariable String codigoBarras)
    {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.visualizarProductoPorCodigoDeBarras(codigoBarras));
    }

    @DeleteMapping("/codigobarras/{codigobarras}")
    public ResponseEntity<Void> eliminarProductoPorCodigoDeBarras(@PathVariable String codigoBarras)
    {
        productoService.eliminarProductoPorCodigoDeBarras(codigoBarras);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
