package ar.edu.utn.gestion_inventario.controller;

import ar.edu.utn.gestion_inventario.dto.existencia.ExistenciaDetailDTO;
import ar.edu.utn.gestion_inventario.dto.existencia.ExistenciaListDTO;
import ar.edu.utn.gestion_inventario.dto.existencia.ExistenciaRequestDTO;
import ar.edu.utn.gestion_inventario.dto.existencia.ExistenciaRequestPutDTO;
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
    public ResponseEntity<ExistenciaDetailDTO> modificarStock(@PathVariable Long id, @Valid @RequestBody ExistenciaRequestPutDTO dto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(existenciaService.modificarStock(id, dto.getCantidad()));
    }

    @GetMapping
    public ResponseEntity<List<ExistenciaListDTO>> listarExistencias()
    {
        return ResponseEntity.status(HttpStatus.OK).body(existenciaService.listarExistencia());
    }

    @GetMapping("/stock/mas/{cantidad}")
    public ResponseEntity<List<ExistenciaListDTO>> listarExistenciasConMasCantidad(@PathVariable Long cantidad)
    {
        return ResponseEntity.status(HttpStatus.OK).body(existenciaService.listarExistenciasConMasCantidad(cantidad));
    }

    @GetMapping("/stock/menos/{cantidad}")
    public ResponseEntity<List<ExistenciaListDTO>> listarExistenciasConMenosCantidad(@PathVariable Long cantidad)
    {
        return ResponseEntity.status(HttpStatus.OK).body(existenciaService.listarExistenciasConMenosCantidad(cantidad));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExistenciaDetailDTO> visualizarExistenciaPorId(@PathVariable Long id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(existenciaService.visualizarExistenciaPorId(id));
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminarExistenciasVencidas()
    {
        existenciaService.eliminarExistenciasVencidas();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarExistencia(@PathVariable Long id)
    {
        existenciaService.eliminarExistencia(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/stock/cero")
    public ResponseEntity<List<ExistenciaListDTO>> listarExistenciasConStock0(){
        return ResponseEntity.status(HttpStatus.OK).body(existenciaService.listarExistenciasConStock0());
    }
}
