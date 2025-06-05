package ar.edu.utn.gestion_inventario.repository;

import ar.edu.utn.gestion_inventario.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findAllByProveedorEmail(String email);
    List<Producto> findAllByCategoria(String categoria);
}
