package com.ciber.rrhhservice.presentation.organizacion.mapper;

import com.ciber.rrhhservice.domain.organizacion.model.DepartamentoModel;
import com.ciber.rrhhservice.presentation.organizacion.dto.DepartamentoResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartamentoDtoMapper {

    @Mapping(target = "departamentoId", source = "departamentoId")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "codigo", source = "codigo")
    @Mapping(target = "presupuesto", source = "presupuesto")
    DepartamentoResponseDto dtoMap(DepartamentoModel model);

}
