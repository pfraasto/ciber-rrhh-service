package com.ciber.rrhhservice.infrastructure.mapper;

import com.ciber.rrhhservice.domain.shared.enums.Estados;
import com.ciber.rrhhservice.domain.usuario.model.UsuarioModel;
import com.ciber.rrhhservice.domain.usuario.model.UsuarioRolModel;
import com.ciber.rrhhservice.domain.usuario.valueobject.Email;
import com.ciber.rrhhservice.domain.usuario.valueobject.Username;
import com.ciber.rrhhservice.infrastructure.persistence.usuario.entity.UsuarioEntity;
import com.ciber.rrhhservice.infrastructure.persistence.usuario.entity.UsuarioRolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", expression = "java(convertirUsername(entity.getUsername()))")
    @Mapping(target = "email", expression = "java(convertirEmail(entity.getEmail()))")
    @Mapping(target = "passwordHash", source = "passwordHash")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "apellido", source = "apellido")
    @Mapping(target = "estado", expression = "java(mapBuscaEstado(entity.getActivo()))")
    @Mapping(target = "roles", expression = "java(mapRoles(entity.getRoles()))")
    UsuarioModel usuarioMap(UsuarioEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", expression = "java(model.getUsername().getValue())")
    @Mapping(target = "email", expression = "java(model.getEmail().getValue())")
    @Mapping(target = "passwordHash", source = "passwordHash")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "apellido", source = "apellido")
    @Mapping(target = "activo", expression = "java(mapEstado(model.getEstado()))")
    @Mapping(target = "roles", ignore = true)  // roles se manejan aparte
    UsuarioEntity usuarioModelToEntity(UsuarioModel model);

    // Metodo auxiliar para mapear roles
    default Set<UsuarioRolModel> mapRoles(Set<UsuarioRolEntity> usuarioRoles) {
        if (usuarioRoles == null) return Collections.emptySet();

        return usuarioRoles.stream().map(ur -> {
            UsuarioRolModel model = new UsuarioRolModel();
            model.setId(ur.getRol().getId());
            model.setNombre(ur.getRol().getNombre());
            model.setDescripcion(ur.getRol().getDescripcion());
            return model;
        }).collect(Collectors.toSet());
    }

    default boolean mapEstado(String estado) {
        return Estados.obtenerValor(Estados.valueOf(estado));
    }

    default String mapBuscaEstado(boolean estado) {
        return Estados.buscar(estado).name();
    }

    default Username convertirUsername(String nombreUsuario) {
        return new Username(nombreUsuario);
    }

    default Email convertirEmail(String correo) {
        return new Email(correo);
    }
}
