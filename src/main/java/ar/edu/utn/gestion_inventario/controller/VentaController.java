package ar.edu.utn.gestion_inventario.controller;

import ar.edu.utn.gestion_inventario.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/venta")
public class VentaController {
    @Autowired
    private VentaService ventaService;
}
