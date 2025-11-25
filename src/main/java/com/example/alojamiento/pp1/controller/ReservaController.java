package com.example.alojamiento.pp1.controller;

import com.example.alojamiento.pp1.dto.ReservaDTO;
import com.example.alojamiento.pp1.model.Reserva;
import com.example.alojamiento.pp1.service.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping()
    public List<Reserva> todasLasReservas() {
        return reservaService.listarTodas();
    }

    @PostMapping()
    public Reserva crearReserva(@RequestBody ReservaDTO reservaDTO) {
        return reservaService.crearReserva(reservaDTO);
    }

    @GetMapping("/{id}")
    public Optional<Reserva> verUnaReserva(@PathVariable Long id) {
        return reservaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Reserva editarReserva(@PathVariable Long id, @RequestBody Reserva reservaNueva) {
        return reservaService.editarReserva(id, reservaNueva);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Reserva> reservasPorUsuario(@PathVariable Long usuarioId) {
        return reservaService.reservasPorUsuario(usuarioId);
    }

    @DeleteMapping("/{id}")
    public void eliminarReserva(@PathVariable Long id) {
        reservaService.eliminar(id);
    }
}
