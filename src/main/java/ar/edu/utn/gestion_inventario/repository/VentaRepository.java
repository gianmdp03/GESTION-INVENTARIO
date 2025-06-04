package ar.edu.utn.gestion_inventario.repository;

import ar.edu.utn.gestion_inventario.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}
