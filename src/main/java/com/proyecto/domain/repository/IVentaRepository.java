package com.proyecto.domain.repository;

import com.proyecto.domain.entity.VentaPasaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVentaRepository extends JpaRepository<VentaPasaje,Long> {
}
