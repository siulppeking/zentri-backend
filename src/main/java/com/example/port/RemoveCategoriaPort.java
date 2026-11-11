package com.example.port;

import com.example.model.Categoria;

import java.util.Optional;

public interface RemoveCategoriaPort {

    Optional<Categoria> findById(Long id, Long idUsuario);

    boolean existsMovimientosByCategoria_Id(Long id, Long idUsuario);

    void deleteById(Long id, Long idUsuario);

}
