package ar.edu.utn.gestion_inventario.repository;

import ar.edu.utn.gestion_inventario.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    Proveedor getReferenceByEmail(String email);
    Optional<Proveedor> findByEmail(String email);
}
