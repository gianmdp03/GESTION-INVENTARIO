package ar.edu.utn.gestion_inventario.controller;

import ar.edu.utn.gestion_inventario.dto.descuento.DescuentoDetailDTO;
import ar.edu.utn.gestion_inventario.dto.descuento.DescuentoListDTO;
import ar.edu.utn.gestion_inventario.dto.descuento.DescuentoRequestDTO;
import ar.edu.utn.gestion_inventario.service.DescuentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/descuento")
public class DescuentoController {
    @Autowired
    private DescuentoService descuentoService;

    @PostMapping
    public ResponseEntity<DescuentoDetailDTO> crearDescuento(@Valid @RequestBody DescuentoRequestDTO dto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(descuentoService.crearDescuento(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DescuentoDetailDTO> modificarDescuento(@PathVariable Long id, @Valid @RequestBody DescuentoRequestDTO dto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(descuentoService.modificarDescuento(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<DescuentoListDTO>> listarDescuentos()
    {
        return ResponseEntity.status(HttpStatus.OK).body(descuentoService.listarDescuentos());
    }
}
