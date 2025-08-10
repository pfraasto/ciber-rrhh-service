package com.ciber.rrhhservice.presentation.organizacion.mapper;

import com.ciber.rrhhservice.domain.organizacion.model.CargoModel;
import com.ciber.rrhhservice.presentation.organizacion.dto.CargoResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CargoDtoMapper {

    @Mapping(target = "id", source = "cargoId")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "nivelJerarquico", source = "nivelJerarquico")
    @Mapping(target = "salarioBase", source = "salarioBase")
    CargoResponseDto dtoMap(CargoModel model);

}
