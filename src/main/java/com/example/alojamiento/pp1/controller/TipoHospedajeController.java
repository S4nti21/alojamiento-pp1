package com.example.alojamiento.pp1.controller;

import com.example.alojamiento.pp1.model.TipoHospedaje;
import com.example.alojamiento.pp1.repository.TipoHospedajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/TipoHospedajes")
public class TipoHospedajeController {

    @Autowired
    private TipoHospedajeRepository tipoHospedajeRepository;

    @GetMapping
    public List<TipoHospedaje> getTodosLosTipos() {
        return tipoHospedajeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<TipoHospedaje> verTipoPorId(@PathVariable Long id) {
        return tipoHospedajeRepository.findById(id);
    }

    @PostMapping
    public TipoHospedaje crearTipo(@RequestBody TipoHospedaje tipoHospedaje) {
        return tipoHospedajeRepository.save(tipoHospedaje);
    }

    @PutMapping("/{id}")
    public TipoHospedaje actualizarTipo(@PathVariable Long id, @RequestBody TipoHospedaje tipoActualizado) {
        return tipoHospedajeRepository.findById(id)
                .map(tipo -> {
                    tipo.setNombre(tipoActualizado.getNombre());
                    return tipoHospedajeRepository.save(tipo);
                }).orElse(null);
    }
    
    @DeleteMapping("/{id}")
    public void eliminarTipo(@PathVariable Long id) {
        tipoHospedajeRepository.deleteById(id);
    }
}
