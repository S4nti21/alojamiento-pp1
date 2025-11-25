package com.example.alojamiento.pp1.service.impl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.alojamiento.pp1.model.Hospedaje;
import com.example.alojamiento.pp1.model.Servicio;
import com.example.alojamiento.pp1.model.Usuario;
import com.example.alojamiento.pp1.repository.HospedajeRepository;
import com.example.alojamiento.pp1.repository.ServicioRepository;
import com.example.alojamiento.pp1.repository.TipoHospedajeRepository;
import com.example.alojamiento.pp1.service.HospedajeService;
import com.example.alojamiento.pp1.service.UsuarioService;

@Service
public class HospedajeServiceImpl implements HospedajeService {

    @Autowired
    private HospedajeRepository hospedajeRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private TipoHospedajeRepository tipoHospedajeRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public List<Hospedaje> listarTodos() {
        return hospedajeRepository.findAll();
    }

    @Override
    public List<Hospedaje> listByUsuario(Long usuarioId) {
        return hospedajeRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public Hospedaje buscarPorId(Long id) {
        return hospedajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hospedaje no encontrado"));
    }

    @Override
    public Hospedaje crear(Hospedaje hospedaje) {

        if (hospedaje.getUsuario() == null || hospedaje.getUsuario().getId() == null) {
            throw new RuntimeException("Debe indicar el usuario que crea el hospedaje");
        }

        Usuario usuario = usuarioService.buscarPorId(hospedaje.getUsuario().getId());
        hospedaje.setUsuario(usuario);

        hospedaje.setFecha_creacion(LocalDate.now().toString());

        if (hospedaje.getTipoHospedaje() != null && hospedaje.getTipoHospedaje().getId() != null) {
            tipoHospedajeRepository.findById(hospedaje.getTipoHospedaje().getId())
                    .ifPresent(hospedaje::setTipoHospedaje);
        }

        if (hospedaje.getServicios() != null && !hospedaje.getServicios().isEmpty()) {
            List<Long> ids = hospedaje.getServicios().stream()
                    .map(Servicio::getId)
                    .toList();

            hospedaje.setServicios(new HashSet<>(servicioRepository.findAllById(ids)));
        }

        return hospedajeRepository.save(hospedaje);
    }

    @Override
    public Hospedaje editar(Long id, Hospedaje nuevo) {

        Hospedaje hospedaje = buscarPorId(id);

        hospedaje.setNombre(nuevo.getNombre());
        hospedaje.setDireccion(nuevo.getDireccion());
        hospedaje.setDescripcion(nuevo.getDescripcion());
        hospedaje.setImagen(nuevo.getImagen());
        hospedaje.setPrecio_por_noche(nuevo.getPrecio_por_noche());
        hospedaje.setFecha_modificacion(LocalDate.now().toString());

        if (nuevo.getTipoHospedaje() != null && nuevo.getTipoHospedaje().getId() != null) {
            tipoHospedajeRepository.findById(nuevo.getTipoHospedaje().getId())
                    .ifPresent(hospedaje::setTipoHospedaje);
        }

        if (nuevo.getServicios() != null && !nuevo.getServicios().isEmpty()) {
            List<Long> ids = nuevo.getServicios().stream()
                    .map(Servicio::getId)
                    .toList();

            hospedaje.setServicios(new HashSet<>(servicioRepository.findAllById(ids)));
        }

        return hospedajeRepository.save(hospedaje);
    }

    @Override
    public void eliminar(Long id) {
        Hospedaje hospedaje = buscarPorId(id);

        hospedaje.getServicios().clear();
        hospedajeRepository.save(hospedaje);

        hospedajeRepository.deleteById(id);
    }
}
