package ar.edu.utn.gestion_inventario.repository;

import ar.edu.utn.gestion_inventario.dto.existencia.ExistenciaListDTO;
import ar.edu.utn.gestion_inventario.model.Existencia;
import ar.edu.utn.gestion_inventario.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExistenciaRepository extends JpaRepository<Existencia, Long> {
    List<Existencia> findAllByCantidadLessThanOrderByCantidadAsc(Long cantidad);
    List<Existencia> findAllByCantidadGreaterThanOrderByCantidadDesc(Long cantidad);
    void deleteByFechaVencimientoBefore(LocalDate fecha);
    @Query("SELECT new ar.edu.utn.gestion_inventario.dto.existencia.ExistenciaListDTO(e.id, e.cantidad, e.fechaVencimiento, e.producto.nombre) FROM Existencia e WHERE e.cantidad = 0")    List<ExistenciaListDTO> findAllWithCantidadZero();
}
