package com.ciber.rrhhservice.infrastructure.persistence.organizacion.mapper;

import com.ciber.rrhhservice.domain.organizacion.model.CargoModel;
import com.ciber.rrhhservice.domain.shared.enums.Estado;
import com.ciber.rrhhservice.infrastructure.persistence.organizacion.entity.CargoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CargoMapper {

    @Mapping(target = "cargoId", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "nivelJerarquico", source = "nivelJerarquico")
    @Mapping(target = "salarioBase", source = "salarioBase")
    @Mapping(target = "departamentoId", source = "departamentoId")
    @Mapping(target = "estado", expression = "java(mapEstado(entity.isActivo()))")
    CargoModel cargoMap(CargoEntity entity);

    default Estado mapEstado(boolean activo) {
        return Estado.buscar(activo);
    }
}
