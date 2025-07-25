package com.ciber.rrhhservice.infrastructure.persistence.usuario.repository;

import com.ciber.rrhhservice.domain.shared.enums.Estados;
import com.ciber.rrhhservice.domain.usuario.model.UsuarioModel;
import com.ciber.rrhhservice.domain.usuario.model.UsuarioRolModel;
import com.ciber.rrhhservice.domain.usuario.repository.UsuarioRepository;
import com.ciber.rrhhservice.infrastructure.mapper.UsuarioMapper;
import com.ciber.rrhhservice.infrastructure.persistence.usuario.entity.UsuarioEntity;
import com.ciber.rrhhservice.infrastructure.persistence.usuario.jpa.RolRepositoryJpa;
import com.ciber.rrhhservice.infrastructure.persistence.usuario.jpa.UsuarioRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioRepositoryJpa usuarioRepository;
    private final RolRepositoryJpa rolRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public List<UsuarioModel> findAllUsuarios() {
        //return usuarioRepository.findAll()
        return usuarioRepository.usuariosConRoles(Estados.ACTIVO.isValor())
                .stream()
                .map(usuarioMapper::usuarioMap)
                .toList();
    }

    @Override
    public Optional<UsuarioModel> findUsuarioById(Long id) {
        return usuarioRepository.findById(id)
                .stream()
                .map(usuarioMapper::usuarioMap)
                .findFirst();
    }

    @Override
    public Optional<UsuarioModel> findUsuarioByUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .stream()
                .map(usuarioMapper::usuarioMap)
                .findFirst();
    }

    @Override
    public UsuarioModel saveUsuario(UsuarioModel usuario) {
        UsuarioEntity entidad = usuarioMapper.usuarioModelToEntity(usuario);
        UsuarioEntity guardado = usuarioRepository.save(entidad);
        return usuarioMapper.usuarioMap(guardado);
    }

    @Override
    public void deleteUsuarioById(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<UsuarioRolModel> findRolById(Long id) {
        return rolRepository.findById(id).stream().map(r -> {
                    UsuarioRolModel usuarioRolModel = new UsuarioRolModel();
                    usuarioRolModel.setId(r.getId());
                    usuarioRolModel.setNombre(r.getNombre());
                    usuarioRolModel.setDescripcion(r.getDescripcion());
                    usuarioRolModel.setEstado(Estados.buscar(r.getActivo()).name());
                    return usuarioRolModel;
                })
                .findFirst();
    }
}
