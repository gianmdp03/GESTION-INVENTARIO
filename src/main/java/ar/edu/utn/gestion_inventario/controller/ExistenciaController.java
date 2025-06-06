package ar.edu.utn.gestion_inventario.controller;

import ar.edu.utn.gestion_inventario.dto.existencia.ExistenciaDetailDTO;
import ar.edu.utn.gestion_inventario.dto.existencia.ExistenciaListDTO;
import ar.edu.utn.gestion_inventario.dto.existencia.ExistenciaRequestDTO;
import ar.edu.utn.gestion_inventario.service.ExistenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/existencia")
public class ExistenciaController {
    @Autowired
    private ExistenciaService existenciaService;

    @PostMapping
    public ResponseEntity<ExistenciaDetailDTO> crearExistencia(@Valid @RequestBody ExistenciaRequestDTO dto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(existenciaService.crearExistencia(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExistenciaDetailDTO> modificarStock(@PathVariable Long id, @PathVariable int stock)
    {
        return ResponseEntity.status(HttpStatus.OK).body(existenciaService.modificarStock(id, stock));
    }

    @GetMapping
    public ResponseEntity<List<ExistenciaListDTO>> listarExistencias()
    {
        return ResponseEntity.status(HttpStatus.OK).body(existenciaService.listarExistencia());
    }

    @GetMapping("/stock/mas")
    public ResponseEntity<List<ExistenciaListDTO>> listarExistenciasConMasCantidad()
    {
        return ResponseEntity.status(HttpStatus.OK).body(existenciaService.listarExistenciasConMasCantidad());
    }

    @GetMapping("/stock/menos/{cantidad}")
    public ResponseEntity<List<ExistenciaListDTO>> listarExistenciasConMenosCantidad(@PathVariable int cantidad)
    {
        return ResponseEntity.status(HttpStatus.OK).body(existenciaService.listarExistenciasConMenosCantidad(cantidad));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExistenciaDetailDTO> visualizarExistenciaPorId(@PathVariable Long id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(existenciaService.visualizarExistenciaPorId(id));
    }

    @DeleteMapping("/{id}")
    public void eliminarExistencia(@PathVariable Long id)
    {
        existenciaService.eliminarExistencia(id);
    }
}
