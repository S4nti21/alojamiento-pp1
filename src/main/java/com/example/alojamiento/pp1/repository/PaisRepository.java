package com.example.alojamiento.pp1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.alojamiento.pp1.model.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {
    
}
