package com.example.alojamiento.pp1.service;

import com.example.alojamiento.pp1.model.TipoHospedaje;

import java.util.List;
import java.util.Optional;

public interface TipoHospedajeService {

    List<TipoHospedaje> findAll();

    Optional<TipoHospedaje> findById(Long id);

    TipoHospedaje save(TipoHospedaje tipo);

    TipoHospedaje update(Long id, TipoHospedaje tipoActualizado);

    void delete(Long id);
}
