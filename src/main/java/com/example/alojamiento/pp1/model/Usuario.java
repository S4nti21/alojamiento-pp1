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
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;
    @Column
    private String contraseña;

    @Column
    private String email;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String fecha_nacimiento;
    @Column
    private String fecha_creacion;
    @Column
    private String fecha_modificacion;

    @ManyToOne
    @JoinColumn(name = "id_tipo_usuario")
    private TipoUsuario TipoUsuario;
}
