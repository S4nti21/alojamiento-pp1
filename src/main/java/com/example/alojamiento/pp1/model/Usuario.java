package com.example.alojamiento.pp1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String contraseña;

    @Column
    private String email;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String imagen;
    @Column
    private int dni;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RolUsuario rol;

}
