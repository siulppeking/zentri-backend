package com.example.port;

import com.example.model.Categoria;

public interface CreateCategoriaPort {

    boolean existsByNombre(String nombre, Long idUsuario);

    Categoria save(Categoria categoria);

}
