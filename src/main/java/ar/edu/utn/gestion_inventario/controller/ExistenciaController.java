package ar.edu.utn.gestion_inventario.controller;

import ar.edu.utn.gestion_inventario.dto.existencia.ExistenciaDetailDTO;
import ar.edu.utn.gestion_inventario.dto.existencia.ExistenciaRequestDTO;
import ar.edu.utn.gestion_inventario.service.ExistenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/existencia")
public class ExistenciaController {
    @Autowired
    private ExistenciaService existenciaService;

    @PostMapping
    public ResponseEntity<ExistenciaDetailDTO> crearExistencia(@Valid @RequestBody ExistenciaRequestDTO dto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(existenciaService.crearExistencia(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExistenciaDetailDTO> modificarStock(@PathVariable Long id, int stock)
    {
        return ResponseEntity.status(HttpStatus.OK).body(existenciaService.modificarStock(id, stock));
    }

    
}
