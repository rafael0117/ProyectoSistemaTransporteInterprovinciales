package com.proyecto.domain.repository;

import com.proyecto.domain.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonalRepository extends JpaRepository<Personal, Long> {
}
