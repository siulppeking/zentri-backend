package com.example.iam.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.exception.UsernameOrPasswordInvalidException;
import com.example.iam.model.Usuario;
import com.example.iam.repository.RolRepository;
import com.example.iam.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository,
                                    RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameOrPasswordInvalidException("Username or password is incorrect 1"));
        usuario.setRoles(rolRepository.findAllByIdUsuario(usuario.getIdUsuario()));
        if (!usuario.getHabilitado()) {
            throw new UsernameOrPasswordInvalidException("User is not enabled");
        }
        if (usuario.getRoles().isEmpty()) {
            throw new UsernameOrPasswordInvalidException("User has no roles assigned");
        }
        return usuario;
    }
}