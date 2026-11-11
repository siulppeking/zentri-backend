package com.example.iam.repository;

import com.example.iam.model.Rol;

import java.util.List;
import java.util.Optional;

public interface RolRepository {

    List<Rol> findAllByIdUsuario(Long idUsuario);

    Optional<Rol> findByNombre(String nombre);

}
