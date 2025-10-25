package com.example.alojamiento.pp1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
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
    @Lob
    private byte[] imagen;
    @Column
    private int dni;

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_tipo_usuario", nullable = true)
    private TipoUsuario tipoUsuario;
}
