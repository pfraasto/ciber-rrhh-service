package com.ciber.rrhhservice.domain.organizacion.repository;

import com.ciber.rrhhservice.domain.organizacion.model.DepartamentoModel;

import java.util.List;

public interface DepartamentoRepository {
    List<DepartamentoModel> findDepartamento();
}
