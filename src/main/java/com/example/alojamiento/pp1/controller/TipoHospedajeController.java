package com.example.alojamiento.pp1.controller;

import com.example.alojamiento.pp1.model.TipoHospedaje;
import com.example.alojamiento.pp1.service.TipoHospedajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tipoHospedaje")
public class TipoHospedajeController {

    @Autowired
    private TipoHospedajeService tipoHospedajeService;

    @GetMapping
    public List<TipoHospedaje> getTodosLosTipos() {
        return tipoHospedajeService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<TipoHospedaje> verTipoPorId(@PathVariable Long id) {
        return tipoHospedajeService.findById(id);
    }

    @PostMapping
    public TipoHospedaje crearTipo(@RequestBody TipoHospedaje tipoHospedaje) {
        return tipoHospedajeService.save(tipoHospedaje);
    }

    @PutMapping("/{id}")
    public TipoHospedaje actualizarTipo(@PathVariable Long id, @RequestBody TipoHospedaje tipoActualizado) {
        return tipoHospedajeService.update(id, tipoActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarTipo(@PathVariable Long id) {
        tipoHospedajeService.delete(id);
    }
}
