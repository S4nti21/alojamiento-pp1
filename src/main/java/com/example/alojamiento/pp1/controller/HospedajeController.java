package com.example.alojamiento.pp1.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.alojamiento.pp1.model.Hospedaje;
import com.example.alojamiento.pp1.model.Servicio;
import com.example.alojamiento.pp1.model.Usuario;
import com.example.alojamiento.pp1.repository.HospedajeRepository;
import com.example.alojamiento.pp1.repository.ServicioRepository;
import com.example.alojamiento.pp1.repository.TipoHospedajeRepository;
import com.example.alojamiento.pp1.repository.UsuarioRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/hospedaje")
public class HospedajeController {

    @Autowired
    private HospedajeRepository hospedajeRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private TipoHospedajeRepository tipoHospedajeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Hospedaje> todosHospedajes() {
        return hospedajeRepository.findAll();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Hospedaje> hospedajesPorUsuario(@PathVariable Long usuarioId) {
        return hospedajeRepository.findByUsuarioId(usuarioId);
    }

    @GetMapping("/{id}")
    public Optional<Hospedaje> verHospedaje(@PathVariable Long id) {
        return hospedajeRepository.findById(id);
    }

    @PostMapping
    public Hospedaje crearHospedaje(@RequestBody Hospedaje hospedaje) {
        if (hospedaje.getUsuario() == null || hospedaje.getUsuario().getId() == null) {
            throw new RuntimeException("Debe indicar el usuario que crea el hospedaje");
        }

        Usuario usuario = usuarioRepository.findById(hospedaje.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        hospedaje.setUsuario(usuario);

        hospedaje.setFecha_creacion(java.time.LocalDate.now().toString());

        if (hospedaje.getTipoHospedaje() != null && hospedaje.getTipoHospedaje().getId() != null) {
            tipoHospedajeRepository.findById(hospedaje.getTipoHospedaje().getId())
                    .ifPresent(hospedaje::setTipoHospedaje);
        }

        if (hospedaje.getServicios() != null && !hospedaje.getServicios().isEmpty()) {
            List<Long> idsServicios = hospedaje.getServicios().stream()
                    .map(Servicio::getId)
                    .toList();
            List<Servicio> servicios = servicioRepository.findAllById(idsServicios);
            hospedaje.setServicios(new HashSet<>(servicios));
        }

        return hospedajeRepository.save(hospedaje);
    }

    @PutMapping("/{id}")
    public Hospedaje editarHospedaje(@PathVariable Long id, @RequestBody Hospedaje hospedajeNuevo) {
        return hospedajeRepository.findById(id)
                .map(hospedaje -> {
                    hospedaje.setNombre(hospedajeNuevo.getNombre());
                    hospedaje.setDireccion(hospedajeNuevo.getDireccion());
                    hospedaje.setDescripcion(hospedajeNuevo.getDescripcion());
                    hospedaje.setImagen(hospedajeNuevo.getImagen());
                    hospedaje.setPrecio_por_noche(hospedajeNuevo.getPrecio_por_noche());
                    hospedaje.setFecha_modificacion(java.time.LocalDate.now().toString());

                    if (hospedajeNuevo.getTipoHospedaje() != null
                            && hospedajeNuevo.getTipoHospedaje().getId() != null) {
                        tipoHospedajeRepository.findById(hospedajeNuevo.getTipoHospedaje().getId())
                                .ifPresent(hospedaje::setTipoHospedaje);
                    }

                    if (hospedajeNuevo.getServicios() != null && !hospedajeNuevo.getServicios().isEmpty()) {
                        List<Long> idsServicios = hospedajeNuevo.getServicios().stream()
                                .map(Servicio::getId)
                                .toList();
                        List<Servicio> servicios = servicioRepository.findAllById(idsServicios);
                        hospedaje.setServicios(new HashSet<>(servicios));
                    }

                    return hospedajeRepository.save(hospedaje);
                })
                .orElseThrow(() -> new RuntimeException("Hospedaje no encontrado"));
    }

    @DeleteMapping("/{id}")
    public void eliminarHospedaje(@PathVariable Long id) {
        Hospedaje hospedaje = hospedajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hospedaje no encontrado"));

        hospedaje.getServicios().clear();
        hospedajeRepository.save(hospedaje);
        hospedajeRepository.deleteById(id);
    }

}