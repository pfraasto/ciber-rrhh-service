package com.ciber.rrhhservice.infrastructure.persistence.organizacion.jpa;

import com.ciber.rrhhservice.infrastructure.persistence.organizacion.entity.DepartamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepositoryJpa extends JpaRepository<DepartamentoEntity, Long> {
}
