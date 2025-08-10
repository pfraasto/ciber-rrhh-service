package com.ciber.rrhhservice.infrastructure.persistence.organizacion.repository;

import com.ciber.rrhhservice.domain.organizacion.model.DepartamentoModel;
import com.ciber.rrhhservice.domain.organizacion.repository.DepartamentoRepository;
import com.ciber.rrhhservice.infrastructure.persistence.organizacion.jpa.DepartamentoRepositoryJpa;
import com.ciber.rrhhservice.infrastructure.persistence.organizacion.mapper.DepartamentoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DepartamentoRepositoryImpl implements DepartamentoRepository {

    private final DepartamentoRepositoryJpa departamentoRepositoryJpa;
    private final DepartamentoMapper departamentoMapper;

    @Override
    public List<DepartamentoModel> findDepartamento() {
        return departamentoRepositoryJpa.findAll()
                .stream()
                .map(departamentoMapper::departamentoMap)
                .toList();
    }
}
