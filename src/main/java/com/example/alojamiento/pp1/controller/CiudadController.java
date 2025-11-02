package com.example.alojamiento.pp1.controller;

import com.example.alojamiento.pp1.model.Ciudad;
import com.example.alojamiento.pp1.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ciudad")
public class CiudadController {

    @Autowired
    private CiudadRepository ciudadRepository;

    @GetMapping
    public List<Ciudad> getTodasLasCiudades() {
        return ciudadRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Ciudad> verCiudadPorId(@PathVariable Long id) {
        return ciudadRepository.findById(id);
    }

    @PostMapping
    public Ciudad crearCiudad(@RequestBody Ciudad ciudad) {
        return ciudadRepository.save(ciudad);
    }

    @PutMapping("/{id}")
    public Ciudad actualizarCiudad(@PathVariable Long id, @RequestBody Ciudad ciudadActualizada) {
        return ciudadRepository.findById(id)
                .map(ciudad -> {
                    ciudad.setNombre(ciudadActualizada.getNombre());
                    return ciudadRepository.save(ciudad);
                }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void eliminarCiudad(@PathVariable Long id) {
        ciudadRepository.deleteById(id);
    }
}
