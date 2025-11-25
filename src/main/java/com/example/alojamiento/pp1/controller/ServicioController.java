package com.example.alojamiento.pp1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.alojamiento.pp1.model.Servicio;
import com.example.alojamiento.pp1.service.ServicioService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/servicio")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @PostMapping
    public Servicio crearServicio(@RequestBody Servicio servicio) {
        return servicioService.crear(servicio);
    }
}
