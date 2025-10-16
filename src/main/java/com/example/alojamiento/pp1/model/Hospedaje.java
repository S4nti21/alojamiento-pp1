package com.example.alojamiento.pp1.model;

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
public class Hospedaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;
    
    @Column
    private String descripcion;

    @Column
    private String imagen;

    @Column
    private Double precio_por_noche;

    @Column
    private String fecha_creacion;

    @Column
    private String fecha_modificacion;

    @ManyToOne
    @JoinColumn(name = "id_ciudad")
    private Ciudad ciudad;

    @ManyToOne
    @JoinColumn(name = "id_tipo_hospedaje")
    private TipoHospedaje TipoHospedaje;
}
