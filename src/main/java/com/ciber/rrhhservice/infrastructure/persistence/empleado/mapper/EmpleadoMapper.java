package com.ciber.rrhhservice.infrastructure.persistence.empleado.mapper;

import com.ciber.rrhhservice.domain.empleado.model.EmpleadoModel;
import com.ciber.rrhhservice.infrastructure.persistence.empleado.entity.EmpleadoEntity;
import com.ciber.rrhhservice.infrastructure.persistence.empleado.projection.EmpleadoProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {

    @Mapping(target = "empleadoId", source = "id")
    @Mapping(target = "numero", source = "numeroEmpleado")
    @Mapping(target = "nombres", source = "nombres")
    @Mapping(target = "apellidos", source = "apellidos")
    @Mapping(target = "documento", source = "numeroEmpleado")
    @Mapping(target = "tipoDocumento", source = "tipoDocumento")
    @Mapping(target = "fechaNacimiento", source = "fechaNacimiento")
    @Mapping(target = "genero", source = "genero")
    @Mapping(target = "estadoCivil", source = "estadoCivil")
    @Mapping(target = "telefono", source = "telefono")
    @Mapping(target = "emailPersonal", source = "emailPersonal")
    @Mapping(target = "emailCorporativo", source = "emailCorporativo")
    @Mapping(target = "direccion", source = "direccion")
    @Mapping(target = "fechaIngreso", source = "fechaIngreso")
    @Mapping(target = "fechaSalida", source = "fechaSalida")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "cargoId", source = "cargoId")
    @Mapping(target = "departamentoId", source = "departamentoId")
    @Mapping(target = "supervisorId", source = "supervisorId")
    EmpleadoModel map(EmpleadoEntity entity);


    @Mapping(target = "id", source = "empleadoId")
    @Mapping(target = "numeroEmpleado", source = "numero")
    @Mapping(target = "nombres", source = "nombres")
    @Mapping(target = "apellidos", source = "apellidos")
    @Mapping(target = "documentoIdentidad", source = "documento")
    @Mapping(target = "tipoDocumento", source = "tipoDocumento")
    @Mapping(target = "fechaNacimiento", source = "fechaNacimiento")
    @Mapping(target = "genero", source = "genero")
    @Mapping(target = "estadoCivil", source = "estadoCivil")
    @Mapping(target = "telefono", source = "telefono")
    @Mapping(target = "emailPersonal", source = "emailPersonal")
    @Mapping(target = "emailCorporativo", source = "emailCorporativo")
    @Mapping(target = "direccion", source = "direccion")
    @Mapping(target = "fechaIngreso", source = "fechaIngreso")
    @Mapping(target = "fechaSalida", source = "fechaSalida")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "cargoId", source = "cargoId")
    @Mapping(target = "departamentoId", source = "departamentoId")
    @Mapping(target = "supervisorId", source = "supervisorId")
    EmpleadoEntity entityMap(EmpleadoModel model);

    @Mapping(target = "empleadoId", source = "id")
    @Mapping(target = "numero", source = "numeroEmpleado")
    @Mapping(target = "nombres", source = "nombres")
    @Mapping(target = "apellidos", source = "apellidos")
    @Mapping(target = "documento", source = "numeroEmpleado")
    @Mapping(target = "tipoDocumento", source = "tipoDocumento")
    @Mapping(target = "fechaNacimiento", source = "fechaNacimiento")
    @Mapping(target = "genero", source = "genero")
    @Mapping(target = "estadoCivil", source = "estadoCivil")
    @Mapping(target = "telefono", source = "telefono")
    @Mapping(target = "emailPersonal", source = "emailPersonal")
    @Mapping(target = "emailCorporativo", source = "emailCorporativo")
    @Mapping(target = "direccion", source = "direccion")
    @Mapping(target = "fechaIngreso", source = "fechaIngreso")
    @Mapping(target = "fechaSalida", source = "fechaSalida")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "cargoId", source = "cargoId")
    @Mapping(target = "cargo", source = "cargo")
    @Mapping(target = "departamentoId", source = "departamentoId")
    @Mapping(target = "departamento", source = "departamento")
    EmpleadoModel projectionMap(EmpleadoProjection projection);
}
