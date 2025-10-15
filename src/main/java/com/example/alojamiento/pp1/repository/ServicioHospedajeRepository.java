package com.example.alojamiento.pp1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.alojamiento.pp1.model.ServicioHospedaje;

@Repository
public interface ServicioHospedajeRepository extends JpaRepository<ServicioHospedaje, Long> {
    
}
