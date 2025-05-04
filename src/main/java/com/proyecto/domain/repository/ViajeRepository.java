package com.proyecto.domain.repository;

import com.proyecto.domain.entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViajeRepository extends JpaRepository<Viaje,Long> {
    List<Viaje> findByDestinoIdDestino(Long idDestino);
}
