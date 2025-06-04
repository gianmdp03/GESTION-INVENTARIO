package ar.edu.utn.gestion_inventario.service;

import ar.edu.utn.gestion_inventario.repository.DescuentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class DescuentoService {
    @Autowired
    private DescuentoRepository descuentoRepository;
}
