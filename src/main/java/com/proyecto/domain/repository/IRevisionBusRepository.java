package com.proyecto.domain.repository;

import com.proyecto.domain.entity.RevisionBus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRevisionBusRepository extends JpaRepository<RevisionBus, Long> {
}
