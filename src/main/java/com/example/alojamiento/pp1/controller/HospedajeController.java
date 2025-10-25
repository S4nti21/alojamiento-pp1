package com.example.alojamiento.pp1.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.alojamiento.pp1.model.Hospedaje;
import com.example.alojamiento.pp1.model.Servicio;
import com.example.alojamiento.pp1.repository.CiudadRepository;
import com.example.alojamiento.pp1.repository.HospedajeRepository;
import com.example.alojamiento.pp1.repository.ServicioRepository;
import com.example.alojamiento.pp1.repository.TipoHospedajeRepository;

@RestController
@RequestMapping("/Hospedaje")
public class HospedajeController {

    @Autowired
    private HospedajeRepository hospedajeRepository;
    @Autowired
    private ServicioRepository servicioRepository;
    @Autowired
    private CiudadRepository ciudadRepository;
    @Autowired
    private TipoHospedajeRepository tipoHospedajeRepository;

    @GetMapping
    public List<Hospedaje> todosHospedajes() {
        return hospedajeRepository.findAll();
    }

    @PostMapping()
    public Hospedaje crearHospedaje(@RequestBody Hospedaje hospedaje) {
        return hospedajeRepository.save(hospedaje);
    }

    @GetMapping("/{id}")
    public Optional<Hospedaje> verHospedaje(@PathVariable Long id) {
        return hospedajeRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Hospedaje editarHospedaje(@PathVariable Long id, @RequestBody Hospedaje hospedajeNuevo) {
        return hospedajeRepository.findById(id)
                .map(hospedaje -> {
                    hospedaje.setNombre(hospedajeNuevo.getNombre());
                    hospedaje.setDescripcion(hospedajeNuevo.getDescripcion());
                    hospedaje.setImagen(hospedajeNuevo.getImagen());
                    hospedaje.setPrecio_por_noche(hospedajeNuevo.getPrecio_por_noche());

                    if (hospedajeNuevo.getCiudad() != null && hospedajeNuevo.getCiudad().getId() != null) {
                        ciudadRepository.findById(hospedajeNuevo.getCiudad().getId())
                                .ifPresent(hospedaje::setCiudad);
                    }

                    if (hospedajeNuevo.getTipoHospedaje() != null
                            && hospedajeNuevo.getTipoHospedaje().getId() != null) {
                        tipoHospedajeRepository.findById(hospedajeNuevo.getTipoHospedaje().getId())
                                .ifPresent(hospedaje::setTipoHospedaje);
                    }

                    if (hospedajeNuevo.getServicios() != null && !hospedajeNuevo.getServicios().isEmpty()) {
                        List<Long> idsServicios = hospedajeNuevo.getServicios().stream()
                                .map(servicio -> servicio.getId())
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
        hospedajeRepository.deleteById(id);
    }

}
