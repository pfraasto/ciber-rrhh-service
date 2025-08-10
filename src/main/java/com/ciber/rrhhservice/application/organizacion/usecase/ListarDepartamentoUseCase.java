package com.ciber.rrhhservice.application.organizacion.usecase;

import com.ciber.rrhhservice.domain.organizacion.model.DepartamentoModel;
import com.ciber.rrhhservice.domain.organizacion.repository.DepartamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListarDepartamentoUseCase {

    private final DepartamentoRepository departamentoRepository;

    public List<DepartamentoModel> listarDepartamento() {
        return departamentoRepository.findDepartamento();
    }

}
