package ar.edu.utn.gestion_inventario.repository;

import ar.edu.utn.gestion_inventario.model.Descuento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DescuentoRepository extends JpaRepository<Descuento, Long> {
    List<Descuento> findAllByFechaFinBefore(LocalDate fecha);
}
