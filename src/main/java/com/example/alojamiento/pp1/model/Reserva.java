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

    @Column
    private LocalDateTime fecha_check_in;
    @Column
    private LocalDateTime fecha_check_out;
    @Column
    private Integer cant_niños;
    @Column
    private Integer cant_adultos;
    @Column
    private Integer cant_mascotas;
    @Column
    private LocalDateTime fecha_creacion;
    @Column
    private LocalDateTime fecha_modificacion;
    @Column
    private Integer cant_bebes;
    @Column
    private Double importe_total;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_hospedaje")
    private Hospedaje hospedaje;

}
