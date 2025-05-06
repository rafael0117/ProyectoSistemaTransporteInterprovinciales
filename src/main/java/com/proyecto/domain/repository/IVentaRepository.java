package com.proyecto.domain.repository;

import com.proyecto.domain.entity.VentaPasaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IVentaRepository extends JpaRepository<VentaPasaje,Long> {
    Optional<VentaPasaje> findByCliente(String cliente);
}
