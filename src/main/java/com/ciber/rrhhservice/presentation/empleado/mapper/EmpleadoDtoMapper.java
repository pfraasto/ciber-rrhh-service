package com.ciber.rrhhservice.presentation.empleado.mapper;

import com.ciber.rrhhservice.domain.empleado.model.EmpleadoModel;
import com.ciber.rrhhservice.presentation.empleado.dto.EmpleadoRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmpleadoDtoMapper {

    @Mapping(target = "nombres", source = "nombres")
    @Mapping(target = "apellidos", source = "apellidos")
    @Mapping(target = "documento", source = "documento")
    @Mapping(target = "tipoDocumento", source = "tipoDocumento")
    @Mapping(target = "fechaNacimiento", source = "fechaNacimiento")
    @Mapping(target = "genero", source = "genero")
    @Mapping(target = "estadoCivil", source = "estadoCivil")
    @Mapping(target = "telefono", source = "telefono")
    @Mapping(target = "emailPersonal", source = "emailPersonal")
    @Mapping(target = "emailCorporativo", source = "emailCorporativo")
    @Mapping(target = "direccion", source = "direccion")
    @Mapping(target = "fechaIngreso", source = "fechaIngreso")
    @Mapping(target = "cargoId", source = "cargoId")
    @Mapping(target = "departamentoId", source = "departamentoId")
    @Mapping(target = "supervisorId", source = "supervisorId")
    EmpleadoModel map(EmpleadoRequestDto dto);

}
