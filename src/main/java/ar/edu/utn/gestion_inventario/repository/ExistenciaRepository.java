package ar.edu.utn.gestion_inventario.repository;

import ar.edu.utn.gestion_inventario.dto.existencia.ExistenciaListDTO;
import ar.edu.utn.gestion_inventario.model.Existencia;
import ar.edu.utn.gestion_inventario.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;


import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExistenciaRepository extends JpaRepository<Existencia, Long> {
    void deleteByFechaVencimientoBefore(LocalDate fecha);
    List<Existencia> findAllByCantidad(int cantidad);
}
