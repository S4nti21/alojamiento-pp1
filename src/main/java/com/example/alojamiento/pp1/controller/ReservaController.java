package com.example.alojamiento.pp1.controller;

import java.util.List;
import java.util.Optional;

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

import com.example.alojamiento.pp1.model.Reserva;
import com.example.alojamiento.pp1.repository.ReservaRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/reserva")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping()
    public List<Reserva> todasLasReservas() {
        return reservaRepository.findAll();
    }

    @PostMapping()
    public Reserva crearReserva(@RequestBody Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @GetMapping("/{id}")
    public Optional<Reserva> verUnaReserva(@PathVariable Long id) {
        return reservaRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Reserva editarHospedaje(@PathVariable Long id, @RequestBody Reserva reservaNueva) {
        return reservaRepository.findById(id)
                .map(reserva -> {
                    reserva.setFecha_check_in(reservaNueva.getFecha_check_in());
                    reserva.setFecha_check_out(reservaNueva.getFecha_check_out());
                    reserva.setFecha_creacion(reservaNueva.getFecha_creacion());
                    reserva.setFecha_modificacion(reservaNueva.getFecha_modificacion());
                    reserva.setImporte_total(reservaNueva.getImporte_total());

                    return reservaRepository.save(reserva);
                })
                .orElseThrow(() -> new RuntimeException("Reserva no encontrado"));

    }

    @DeleteMapping("/{id}")
    public void eliminarReserva(@PathVariable Long id) {
        reservaRepository.deleteById(id);
    }

}
