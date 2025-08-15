package com.ciber.rrhhservice.infrastructure.persistence.empleado.repository;

import com.ciber.rrhhservice.domain.empleado.model.EmpleadoModel;
import com.ciber.rrhhservice.domain.empleado.repository.EmpleadoRepository;
import com.ciber.rrhhservice.domain.empleado.valueobject.PaginaResult;
import com.ciber.rrhhservice.domain.empleado.valueobject.PaginacionRequest;
import com.ciber.rrhhservice.infrastructure.persistence.empleado.jpa.EmpleadoRepositoryJpa;
import com.ciber.rrhhservice.infrastructure.persistence.empleado.mapper.EmpleadoMapper;
import com.ciber.rrhhservice.infrastructure.persistence.empleado.projection.EmpleadoProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmpleadoRepositoryImpl implements EmpleadoRepository {

    private final EmpleadoRepositoryJpa empleadoRepositoryJpa;
    private final EmpleadoMapper empleadoMapper;

    @Override
    public EmpleadoModel guardarEmpleado(EmpleadoModel empleado) {
        return empleadoMapper.map(
                empleadoRepositoryJpa.save(
                        empleadoMapper.entityMap(empleado)
                )
        );
    }

    @Override
    public boolean existeEmpleado(String numeroDocumento) {
        return empleadoRepositoryJpa.findByDocumentoIdentidad(numeroDocumento).isPresent();
    }

    @Override
    public Integer ultimoNumeroEmpleado() {
        return empleadoRepositoryJpa.ultimoNumeroEmpleado();
    }

    @Override
    public PaginaResult<EmpleadoModel> listarEmpleados(PaginacionRequest paginacion) {
        Pageable pageable = createPageable(paginacion);
        Page<EmpleadoProjection> page = empleadoRepositoryJpa.findByAllEmpleados(pageable);

        List<EmpleadoModel> empleados = page.getContent()
                .stream()
                .map(empleadoMapper::projectionMap)
                .toList();

        return PaginaResult.of(
                empleados,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements()
        );
    }


    private Pageable createPageable(PaginacionRequest paginacion) {
        Sort sort = paginacion.isAscendente() ?
                Sort.by(paginacion.getOrdenarPor()).ascending() :
                Sort.by(paginacion.getOrdenarPor()).descending();

        return PageRequest.of(paginacion.getPagina(), paginacion.getTamanio(), sort);
    }
}
