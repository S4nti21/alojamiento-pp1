package com.example.alojamiento.pp1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.alojamiento.pp1.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    Optional<Usuario> findByEmailAndContraseña(String email, String contraseña);
}
