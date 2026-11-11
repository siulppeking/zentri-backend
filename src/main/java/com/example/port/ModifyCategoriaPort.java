package com.example.port;

import com.example.model.Categoria;

import java.util.Optional;

public interface ModifyCategoriaPort {

    Optional<Categoria> findById(Long id, Long idUsuario);

    boolean existsByNombreAndIdNot(String nombre, Long id, Long idUsuario);

    Categoria update(Categoria categoria);

}
