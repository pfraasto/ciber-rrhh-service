package com.ciber.rrhhservice.application.usuario.usecase;

import com.ciber.rrhhservice.domain.usuario.model.UsuarioModel;
import com.ciber.rrhhservice.domain.usuario.model.UsuarioRolModel;
import com.ciber.rrhhservice.domain.usuario.repository.UsuarioRepository;
import com.ciber.rrhhservice.domain.usuario.service.UsuarioService;
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

    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAllUsuarios();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioModel> buscarPorId(Long id) {
        return usuarioRepository.findUsuarioById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioModel> buscarPorUsername(String username) {
        return usuarioRepository.findUsuarioByUsername(username);
    }

    @Override
    @Transactional
    public UsuarioModel guardar(UsuarioModel usuario) {

        if (usuario.getRoles() != null && !usuario.getRoles().isEmpty()) {
            Set<UsuarioRolModel> rolesValidados = new HashSet<>();

            for (UsuarioRolModel rolModel : usuario.getRoles()) {
                // Validar que el rol existe
                usuarioRepository.findRolById(rolModel.getId())
                        .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + rolModel.getId()));

                // Si llegamos aquí, el rol es válido
                rolesValidados.add(rolModel);
            }

            usuario.setRoles(rolesValidados);
        }

        return usuarioRepository.saveUsuario(usuario);
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteUsuarioById(id);
    }
}
