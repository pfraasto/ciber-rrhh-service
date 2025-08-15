package com.ciber.rrhhservice.presentation.empleado.controller;

import com.ciber.rrhhservice.domain.empleado.model.EmpleadoModel;
import com.ciber.rrhhservice.domain.empleado.service.EmpleadoService;
import com.ciber.rrhhservice.domain.empleado.valueobject.PaginaResult;
import com.ciber.rrhhservice.domain.empleado.valueobject.PaginacionRequest;
import com.ciber.rrhhservice.presentation.empleado.dto.EmpleadoRequestDto;
import com.ciber.rrhhservice.presentation.empleado.mapper.EmpleadoDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final EmpleadoDtoMapper empleadoDtoMapper;

    @PostMapping
    public void registrar(@Valid @RequestBody EmpleadoRequestDto request) {
        empleadoService.registrarEmpleado(empleadoDtoMapper.map(request));
    }

    @GetMapping
    public ResponseEntity<PaginaResult<EmpleadoModel>> listarEmpleados(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio,
            @RequestParam(defaultValue = "id") String ordenarPor,
            @RequestParam(defaultValue = "asc") String direccion) {

        PaginacionRequest paginacion = PaginacionRequest.builder()
                .pagina(pagina)
                .tamanio(tamanio)
                .ordenarPor(ordenarPor)
                .direccion(direccion)
                .build();

        PaginaResult<EmpleadoModel> resultado = empleadoService.listarEmpleados(paginacion);
        return ResponseEntity.ok(resultado);
    }

}
