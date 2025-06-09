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
    public ResponseEntity<ProductoDetailDTO> modificarProducto(@PathVariable Long id, @Valid @RequestBody ProductoRequestPutDTO dto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.modificarProducto(id, dto));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ProductoDetailDTO> modificarPrecioPorId(@PathVariable Long id, @Valid @RequestBody ProductoRequestPatchDTO dto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.modificarPrecioPorID(id, dto));
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

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDetailDTO> visualizarProductoPorId(@PathVariable Long id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.visualizarProductoPorId(id));
    }

    @GetMapping("/codigobarras")
    public ResponseEntity<ProductoDetailDTO> visualizarProductoPorCodigoDeBarras(@Valid @RequestBody ProductoRequestGetDTO dto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.visualizarProductoPorCodigoDeBarras(dto.getCodigoBarras()));
    }

    @DeleteMapping("/codigobarras")
    public ResponseEntity<Void> eliminarProductoPorCodigoDeBarras(@Valid @RequestBody ProductoRequestGetDTO dto)
    {
        productoService.eliminarProductoPorCodigoDeBarras(dto.getCodigoBarras());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
