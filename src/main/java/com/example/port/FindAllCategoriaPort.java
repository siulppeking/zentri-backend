package com.example.port;

import com.example.model.Categoria;

import java.util.List;

public interface FindAllCategoriaPort {

    List<Categoria> findAll(Long idUsuario);

}
