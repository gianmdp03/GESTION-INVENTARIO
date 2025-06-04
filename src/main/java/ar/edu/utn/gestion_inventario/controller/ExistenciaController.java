package ar.edu.utn.gestion_inventario.controller;

import ar.edu.utn.gestion_inventario.service.ExistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/existencia")
public class ExistenciaController {
    @Autowired
    private ExistenciaService existenciaService;
}
