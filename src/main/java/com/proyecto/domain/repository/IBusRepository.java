package com.proyecto.domain.repository;

import com.proyecto.domain.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBusRepository extends JpaRepository<Bus,Long> {
}
