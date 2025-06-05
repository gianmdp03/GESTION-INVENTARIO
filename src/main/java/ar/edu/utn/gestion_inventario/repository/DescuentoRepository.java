package ar.edu.utn.gestion_inventario.repository;

import ar.edu.utn.gestion_inventario.model.Descuento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DescuentoRepository extends JpaRepository<Descuento, Long> {
    List<Descuento> findAllByFechaFinBefore(LocalDate fecha);
    List<Descuento> findAllByOrderByFechaInicioAsc();
}
