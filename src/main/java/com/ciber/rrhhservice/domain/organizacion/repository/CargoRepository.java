package com.ciber.rrhhservice.domain.organizacion.repository;

import com.ciber.rrhhservice.domain.organizacion.model.CargoModel;

import java.util.List;

public interface CargoRepository {
    List<CargoModel> findCargos();
}
