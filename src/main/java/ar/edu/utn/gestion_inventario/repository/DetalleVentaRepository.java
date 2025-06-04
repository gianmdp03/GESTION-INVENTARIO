package ar.edu.utn.gestion_inventario.repository;

import ar.edu.utn.gestion_inventario.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
}
