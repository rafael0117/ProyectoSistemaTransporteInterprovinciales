package com.proyecto.domain.repository;

import com.proyecto.domain.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepository extends JpaRepository<Rol,String> {
}
