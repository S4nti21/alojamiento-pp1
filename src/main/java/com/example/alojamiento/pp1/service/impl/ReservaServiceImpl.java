package com.example.alojamiento.pp1.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.alojamiento.pp1.dto.ReservaDTO;
import com.example.alojamiento.pp1.model.Hospedaje;
import com.example.alojamiento.pp1.model.Reserva;
import com.example.alojamiento.pp1.model.Usuario;
import com.example.alojamiento.pp1.repository.HospedajeRepository;
import com.example.alojamiento.pp1.repository.ReservaRepository;
import com.example.alojamiento.pp1.service.ReservaService;
import com.example.alojamiento.pp1.service.UsuarioService;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private HospedajeRepository hospedajeRepository;

    @Override
    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }

    @Override
    public Reserva crearReserva(ReservaDTO reservaDTO) {

        Reserva reserva = new Reserva();
        reserva.setFechaCheckIn(reservaDTO.getFechaCheckIn());
        reserva.setFechaCheckOut(reservaDTO.getFechaCheckOut());

        Usuario usuario = usuarioService.buscarPorId(reservaDTO.getUsuarioId());
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        Hospedaje hospedaje = hospedajeRepository.findById(reservaDTO.getAlojamientoId())
                .orElseThrow(() -> new RuntimeException("Hospedaje no encontrado"));

        reserva.setUsuario(usuario);
        reserva.setHospedaje(hospedaje);

        return reservaRepository.save(reserva);
    }

    @Override
    public Optional<Reserva> buscarPorId(Long id) {
        return reservaRepository.findById(id);
    }

    @Override
    public Reserva editarReserva(Long id, Reserva reservaNueva) {
        return reservaRepository.findById(id)
                .map(reserva -> {
                    reserva.setFechaCheckIn(reservaNueva.getFechaCheckIn());
                    reserva.setFechaCheckOut(reservaNueva.getFechaCheckOut());
                    reserva.setFechaCreacion(reservaNueva.getFechaCreacion());
                    reserva.setFechaModificacion(reservaNueva.getFechaModificacion());
                    reserva.setImporteTotal(reservaNueva.getImporteTotal());
                    return reservaRepository.save(reserva);
                })
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }

    @Override
    public List<Reserva> reservasPorUsuario(Long usuarioId) {
        List<Hospedaje> alojamientos = hospedajeRepository.findByUsuarioId(usuarioId);
        return reservaRepository.findAll().stream()
                .filter(r -> alojamientos.stream().anyMatch(h -> h.getId().equals(r.getHospedaje().getId())))
                .toList();
    }

    @Override
    public List<Reserva> reservasPorHuesped(Long usuarioId) {
        return reservaRepository.findAll().stream()
                .filter(r -> r.getUsuario().getId().equals(usuarioId))
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        reservaRepository.deleteById(id);
    }
}
