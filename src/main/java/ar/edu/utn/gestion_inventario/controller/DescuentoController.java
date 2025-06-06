package ar.edu.utn.gestion_inventario.controller;

import ar.edu.utn.gestion_inventario.dto.descuento.DescuentoDetailDTO;
import ar.edu.utn.gestion_inventario.dto.descuento.DescuentoRequestDTO;
import ar.edu.utn.gestion_inventario.service.DescuentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
