package com.ciber.rrhhservice.presentation.organizacion.controller;

import com.ciber.rrhhservice.domain.organizacion.service.CargoService;
import com.ciber.rrhhservice.presentation.organizacion.dto.CargoResponseDto;

import com.ciber.rrhhservice.presentation.organizacion.mapper.CargoDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cargos")
@RequiredArgsConstructor
public class CargoController {

    private final CargoService cargoService;
    private final CargoDtoMapper cargoDtoMapper;

    @GetMapping
    public List<CargoResponseDto> cargos() {
        return cargoService.listarCargos()
                .stream()
                .map(cargoDtoMapper::dtoMap)
                .toList();
    }

}
