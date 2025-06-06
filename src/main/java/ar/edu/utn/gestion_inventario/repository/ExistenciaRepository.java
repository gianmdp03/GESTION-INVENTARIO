package ar.edu.utn.gestion_inventario.repository;

import ar.edu.utn.gestion_inventario.model.Existencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExistenciaRepository extends JpaRepository<Existencia, Long> {
    List<Existencia> findAllByCantidadLessThanOrderByCantidadAsc(int cantidad);
    List<Existencia> findAllByCantidadGreaterThanOrderByCantidadDesc(int cantidad);
}
