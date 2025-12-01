package com.example.alojamiento.pp1.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Hospedaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String direccion;

    @Column
    private String descripcion;

    @Column(columnDefinition = "LONGTEXT")
    private String imagen;

    @Column
    private Double precio_por_noche;

    @Column
    private String fecha_creacion;

    @Column
    private String fecha_modificacion;

    @ManyToOne
    @JoinColumn(name = "id_tipo_hospedaje")
    private TipoHospedaje TipoHospedaje;

    @ManyToMany
    @JoinTable(name = "servicio_hospedaje", joinColumns = @JoinColumn(name = "id_hospedaje"), inverseJoinColumns = @JoinColumn(name = "id_servicio"))
    private Set<Servicio> servicios;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}
