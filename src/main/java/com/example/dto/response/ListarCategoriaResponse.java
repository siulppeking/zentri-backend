package com.example.dto.response;

import lombok.Getter;

@Getter
public class ListarCategoriaResponse {

    private final Long id;
    private final String nombre;

    public ListarCategoriaResponse(
            Long id,
            String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

}
