package com.ciber.rrhhservice.infrastructure.persistence.organizacion.jpa;

import com.ciber.rrhhservice.infrastructure.persistence.organizacion.entity.CargoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepositoryJpa extends JpaRepository<CargoEntity, Long> {
}
