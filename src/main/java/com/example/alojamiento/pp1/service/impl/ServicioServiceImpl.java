package com.example.alojamiento.pp1.service.impl;

import com.example.alojamiento.pp1.model.Servicio;
import com.example.alojamiento.pp1.repository.ServicioRepository;
import com.example.alojamiento.pp1.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioServiceImpl implements ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public Servicio crear(Servicio servicio) {
        return servicioRepository.save(servicio);
    }
}
