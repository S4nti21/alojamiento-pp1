package com.example.alojamiento.pp1.service.impl;

import com.example.alojamiento.pp1.model.TipoHospedaje;
import com.example.alojamiento.pp1.repository.TipoHospedajeRepository;
import com.example.alojamiento.pp1.service.TipoHospedajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoHospedajeServiceImpl implements TipoHospedajeService {

    @Autowired
    private TipoHospedajeRepository tipoHospedajeRepository;

    @Override
    public List<TipoHospedaje> findAll() {
        return tipoHospedajeRepository.findAll();
    }

    @Override
    public Optional<TipoHospedaje> findById(Long id) {
        return tipoHospedajeRepository.findById(id);
    }

    @Override
    public TipoHospedaje save(TipoHospedaje tipo) {
        return tipoHospedajeRepository.save(tipo);
    }

    @Override
    public TipoHospedaje update(Long id, TipoHospedaje tipoActualizado) {
        return tipoHospedajeRepository.findById(id)
                .map(tipo -> {
                    tipo.setNombre(tipoActualizado.getNombre());
                    return tipoHospedajeRepository.save(tipo);
                }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        tipoHospedajeRepository.deleteById(id);
    }
}
