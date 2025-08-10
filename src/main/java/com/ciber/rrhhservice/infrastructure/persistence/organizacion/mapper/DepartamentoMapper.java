package com.ciber.rrhhservice.infrastructure.persistence.organizacion.mapper;

import com.ciber.rrhhservice.domain.organizacion.model.DepartamentoModel;
import com.ciber.rrhhservice.domain.shared.enums.Estado;
import com.ciber.rrhhservice.infrastructure.persistence.organizacion.entity.DepartamentoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartamentoMapper {

    @Mapping(target = "departamentoId", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "codigo", source = "codigo")
    @Mapping(target = "presupuesto", source = "presupuesto")
    @Mapping(target = "estado", expression = "java(mapEstado(entity.isActivo()))")
    DepartamentoModel departamentoMap(DepartamentoEntity entity);

    default Estado mapEstado(boolean activo) {
        return Estado.buscar(activo);
    }
}
