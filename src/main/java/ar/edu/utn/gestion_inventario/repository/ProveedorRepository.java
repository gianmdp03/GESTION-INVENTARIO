package ar.edu.utn.gestion_inventario.repository;

import ar.edu.utn.gestion_inventario.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    Proveedor getReferenceByEmail(String email);
}
