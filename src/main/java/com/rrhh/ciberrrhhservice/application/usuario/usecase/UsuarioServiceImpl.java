package com.rrhh.ciberrrhhservice.application.usuario.usecase;

import com.rrhh.ciberrrhhservice.application.usuario.enums.Estados;
import com.rrhh.ciberrrhhservice.application.usuario.mapper.UsuarioMapper;
import com.rrhh.ciberrrhhservice.domain.usuario.model.UsuarioModel;
import com.rrhh.ciberrrhhservice.domain.usuario.model.UsuarioRolModel;
import com.rrhh.ciberrrhhservice.domain.usuario.service.UsuarioService;
import com.rrhh.ciberrrhhservice.infrastructure.persistence.usuario.entity.RolEntity;
import com.rrhh.ciberrrhhservice.infrastructure.persistence.usuario.entity.UsuarioEntity;
import com.rrhh.ciberrrhhservice.infrastructure.persistence.usuario.entity.UsuarioRolEntity;
import com.rrhh.ciberrrhhservice.infrastructure.persistence.usuario.jpa.RolRepositoryJpa;
import com.rrhh.ciberrrhhservice.infrastructure.persistence.usuario.jpa.UsuarioRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepositoryJpa usuarioRepository;
    private final RolRepositoryJpa rolRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioModel> listarUsuarios() {
        //return usuarioRepository.findAll()
        return usuarioRepository.usuariosConRoles(Estados.ACTIVO.isValor())
                .stream()
                .map(usuarioMapper::usuarioMap)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioModel> buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .stream()
                .map(usuarioMapper::usuarioMap)
                .findFirst();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioModel> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .stream()
                .map(usuarioMapper::usuarioMap)
                .findFirst();
    }

    @Override
    @Transactional
    public UsuarioModel guardar(UsuarioModel usuario) {

        UsuarioEntity usuarioEntity = usuarioMapper.usuarioModelToEntity(usuario);

        if (usuario.getRoles() != null && !usuario.getRoles().isEmpty()) {
            Set<UsuarioRolEntity> usuarioRoles = new HashSet<>();
            for (UsuarioRolModel rolModel : usuario.getRoles()) {
                RolEntity rol = rolRepository.findById(rolModel.getId())
                        .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + rolModel.getId()));

                UsuarioRolEntity usuarioRol = UsuarioRolEntity.builder()
                        .rol(rol)
                        .usuario(usuarioEntity)
                        .activo(Estados.ACTIVO.isValor())
                        .build();

                usuarioRoles.add(usuarioRol);
            }
            usuarioEntity.setRoles(usuarioRoles);
        }

        UsuarioEntity usuarioGuardado = usuarioRepository.save(usuarioEntity);

        return usuarioMapper.usuarioMap(usuarioGuardado);
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
