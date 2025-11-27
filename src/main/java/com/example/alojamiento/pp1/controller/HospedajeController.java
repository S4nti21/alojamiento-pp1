package com.example.alojamiento.pp1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.alojamiento.pp1.model.Hospedaje;
import com.example.alojamiento.pp1.service.HospedajeService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/hospedaje")
public class HospedajeController {

    @Autowired
    private HospedajeService hospedajeService;

    @GetMapping
    public List<Hospedaje> todosHospedajes() {
        return hospedajeService.listarTodos();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Hospedaje> hospedajesPorUsuario(@PathVariable Long usuarioId) {
        return hospedajeService.listByUsuario(usuarioId);
    }

    @GetMapping("/{id}")
    public Hospedaje verHospedaje(@PathVariable Long id) {
        return hospedajeService.buscarPorId(id);
    }

    @PostMapping
    public Hospedaje crearHospedaje(@RequestBody Hospedaje hospedaje) {
        return hospedajeService.crear(hospedaje);
    }

    @PutMapping("/{id}")
    public Hospedaje editarHospedaje(@PathVariable Long id, @RequestBody Hospedaje hospedajeNuevo) {
        return hospedajeService.editar(id, hospedajeNuevo);
    }

    @DeleteMapping("/{id}")
    public void eliminarHospedaje(@PathVariable Long id) {
        hospedajeService.eliminar(id);
    }

}
