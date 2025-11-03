package com.example.alojamiento.pp1.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.alojamiento.pp1.model.Hospedaje;

@Repository
public interface HospedajeRepository extends JpaRepository<Hospedaje, Long> {
    List<Hospedaje> findByUsuarioId(Long usuarioId);
}
