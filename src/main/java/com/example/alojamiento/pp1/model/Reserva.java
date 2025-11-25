package com.example.alojamiento.pp1.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_check_in")
    private LocalDateTime fechaCheckIn;

    @Column(name = "fecha_check_out")
    private LocalDateTime fechaCheckOut;

    @Column(name = "cant_ninos")
    private Integer cantNinos;

    @Column(name = "cant_adultos")
    private Integer cantAdultos;

    @Column(name = "cant_mascotas")
    private Integer cantMascotas;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "cant_bebes")
    private Integer cantBebes;

    @Column(name = "importe_total")
    private Double importeTotal;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_hospedaje")
    private Hospedaje hospedaje;
}
