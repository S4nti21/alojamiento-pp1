package com.example.alojamiento.pp1.service;

import java.util.List;
import java.util.Optional;

import com.example.alojamiento.pp1.dto.ReservaDTO;
import com.example.alojamiento.pp1.model.Reserva;

public interface ReservaService {

    List<Reserva> listarTodas();

    Reserva crearReserva(ReservaDTO reservaDTO);

    Optional<Reserva> buscarPorId(Long id);

    Reserva editarReserva(Long id, Reserva reservaNueva);

    List<Reserva> reservasPorUsuario(Long usuarioId);

    void eliminar(Long id);

    public List<Reserva> reservasPorHuesped(Long usuarioId);
}
