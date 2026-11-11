package com.example.port;

import com.example.model.Categoria;

import java.util.Optional;

public interface FindCategoriaByIdPort {

    Optional<Categoria> findById(Long id, Long idUsuario);

}
