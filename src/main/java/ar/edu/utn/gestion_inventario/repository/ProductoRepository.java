package ar.edu.utn.gestion_inventario.repository;

import ar.edu.utn.gestion_inventario.model.Producto;
import ar.edu.utn.gestion_inventario.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findAllByProveedor(Proveedor proveedor);
    List<Producto> findAllByCategoria(String categoria);
}
