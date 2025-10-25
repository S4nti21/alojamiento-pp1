package com.example.alojamiento.pp1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.alojamiento.pp1.model.Servicio;
import com.example.alojamiento.pp1.repository.ServicioRepository;

@RestController
@RequestMapping("/Servicio")
public class ServicioController {

    @Autowired
    private ServicioRepository servicioRepository;

    @PostMapping
    public Servicio crearServicio(@RequestBody Servicio servicio) {
        return servicioRepository.save(servicio);
    }
}
