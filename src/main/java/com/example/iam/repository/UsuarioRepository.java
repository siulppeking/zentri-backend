package com.example.iam.repository;

import com.example.iam.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository {

    Optional<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);

    Usuario save(Usuario usuario);

    boolean saveDetailUsuarioRol(Long idUsuario, Long idRol);

}
