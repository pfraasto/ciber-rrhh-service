package com.ciber.rrhhservice.application.organizacion.usecase;

import com.ciber.rrhhservice.domain.organizacion.model.CargoModel;
import com.ciber.rrhhservice.domain.organizacion.repository.CargoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListarCargoUseCase {

    private final CargoRepository cargoRepository;

    public List<CargoModel> listarCargos() {
        return cargoRepository.findCargos();
    }

}
