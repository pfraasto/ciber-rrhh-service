package com.ciber.rrhhservice.presentation.organizacion.controller;

import com.ciber.rrhhservice.domain.organizacion.service.DepartamentoService;
import com.ciber.rrhhservice.presentation.organizacion.dto.DepartamentoResponseDto;
import com.ciber.rrhhservice.presentation.organizacion.mapper.DepartamentoDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
@RequiredArgsConstructor
public class DepartamentoController {

    private final DepartamentoService departamentoService;
    private final DepartamentoDtoMapper departamentoDtoMapper;

    @GetMapping
    public List<DepartamentoResponseDto> departamentos() {
        return departamentoService.listarDepartamento()
                .stream()
                .map(departamentoDtoMapper::dtoMap)
                .toList();
    }

}
