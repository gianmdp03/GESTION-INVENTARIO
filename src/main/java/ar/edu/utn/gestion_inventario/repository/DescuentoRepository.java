package ar.edu.utn.gestion_inventario.repository;

import ar.edu.utn.gestion_inventario.model.Descuento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescuentoRepository extends JpaRepository<Descuento, Long> {
}
