package ar.edu.utn.gestion_inventario.service;

import ar.edu.utn.gestion_inventario.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;
}
