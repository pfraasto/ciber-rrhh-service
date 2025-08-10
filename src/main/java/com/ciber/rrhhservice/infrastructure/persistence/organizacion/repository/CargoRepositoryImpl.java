package com.ciber.rrhhservice.infrastructure.persistence.organizacion.repository;

import com.ciber.rrhhservice.domain.organizacion.model.CargoModel;
import com.ciber.rrhhservice.domain.organizacion.repository.CargoRepository;
import com.ciber.rrhhservice.infrastructure.persistence.organizacion.jpa.CargoRepositoryJpa;
import com.ciber.rrhhservice.infrastructure.persistence.organizacion.mapper.CargoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CargoRepositoryImpl implements CargoRepository {

    private final CargoRepositoryJpa cargoRepositoryJpa;
    private final CargoMapper cargoMapper;

    @Override
    public List<CargoModel> findCargos() {
        return cargoRepositoryJpa.findAll()
                .stream()
                .map(cargoMapper::cargoMap)
                .toList();
    }
}
