package ar.edu.utn.gestion_inventario.controller;

import ar.edu.utn.gestion_inventario.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/detalleVenta")
public class DetalleVentaController {
    @Autowired
    private DetalleVentaService detalleVentaService;
}
