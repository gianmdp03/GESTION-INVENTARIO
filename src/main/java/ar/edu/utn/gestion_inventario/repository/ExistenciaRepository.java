package ar.edu.utn.gestion_inventario.repository;

import ar.edu.utn.gestion_inventario.model.Existencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExistenciaRepository extends JpaRepository<Existencia, Long> {
}
