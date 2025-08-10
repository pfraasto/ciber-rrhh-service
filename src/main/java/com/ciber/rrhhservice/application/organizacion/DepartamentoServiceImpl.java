package com.ciber.rrhhservice.application.organizacion;

import com.ciber.rrhhservice.application.organizacion.usecase.ListarDepartamentoUseCase;
import com.ciber.rrhhservice.domain.organizacion.model.DepartamentoModel;
import com.ciber.rrhhservice.domain.organizacion.service.DepartamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartamentoServiceImpl implements DepartamentoService {

    private final ListarDepartamentoUseCase listarDepartamentoUseCase;

    @Override
    public List<DepartamentoModel> listarDepartamento() {
        return listarDepartamentoUseCase.listarDepartamento();
    }
}
