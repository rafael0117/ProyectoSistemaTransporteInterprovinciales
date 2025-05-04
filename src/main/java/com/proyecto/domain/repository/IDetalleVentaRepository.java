package com.proyecto.domain.repository;

import com.proyecto.domain.entity.DetalleVentaPasaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDetalleVentaRepository extends JpaRepository<DetalleVentaPasaje,Long> {
}
