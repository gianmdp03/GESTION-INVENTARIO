package ar.edu.utn.gestion_inventario.repository;

import ar.edu.utn.gestion_inventario.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findAllByProveedorEmail(String email);
    List<Producto> findAllByCategoria(String categoria);
    Optional<Producto> findByCodigoBarras(String codigoBarras);
    void deleteByCodigoBarras(String codigo);
    boolean existsByCodigoBarras(String codigo);
}
