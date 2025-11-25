package com.example.alojamiento.pp1.service;

import java.util.List;

import com.example.alojamiento.pp1.model.Hospedaje;

public interface HospedajeService {

    List<Hospedaje> listarTodos();

    List<Hospedaje> listByUsuario(Long usuarioId);

    Hospedaje buscarPorId(Long id);

    Hospedaje crear(Hospedaje hospedaje);

    Hospedaje editar(Long id, Hospedaje hospedajeNuevo);

    void eliminar(Long id);
}
