package com.example.dto.response;

import lombok.Getter;

@Getter
public class CreateCategoriaResponse {

    private final Long id;
    private final String nombre;

    public CreateCategoriaResponse(
            Long id,
            String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
