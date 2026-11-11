package com.example.dto.response;

import lombok.Getter;

@Getter
public class ModifyCategoriaResponse {

    private final Long id;
    private final String nombre;

    public ModifyCategoriaResponse(
            Long id,
            String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
