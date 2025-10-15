package com.example.alojamiento.pp1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class ServicioHospedaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_servicio;
    private Long id_hospeaje;

    @ManyToMany
    @JoinColumn(name = "id_servicio")
    private Servicio servicio;

    @ManyToMany
    @JoinColumn(name = "id_hospedaje")
    private Hospedaje hospedaje;
}
