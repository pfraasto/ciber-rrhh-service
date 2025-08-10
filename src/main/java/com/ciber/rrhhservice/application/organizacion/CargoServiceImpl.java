package com.ciber.rrhhservice.application.organizacion;

import com.ciber.rrhhservice.application.organizacion.usecase.ListarCargoUseCase;
import com.ciber.rrhhservice.domain.organizacion.model.CargoModel;
import com.ciber.rrhhservice.domain.organizacion.service.CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CargoServiceImpl implements CargoService {

    private final ListarCargoUseCase listarCargoUseCase;

    @Override
    public List<CargoModel> listarCargos() {
        return listarCargoUseCase.listarCargos();
    }
}
