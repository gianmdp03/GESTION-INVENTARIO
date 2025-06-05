package ar.edu.utn.gestion_inventario.controller;

import ar.edu.utn.gestion_inventario.dto.existencia.ExistenciaDetailDTO;
import ar.edu.utn.gestion_inventario.dto.existencia.ExistenciaRequestDTO;
import ar.edu.utn.gestion_inventario.service.ExistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/existencia")
public class ExistenciaController {
    @Autowired
    private ExistenciaService existenciaService;

    @PostMapping
    public ResponseEntity<ExistenciaDetailDTO> crearExistencia(ExistenciaRequestDTO dto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(existenciaService.crearExistencia(dto));
    }
}
