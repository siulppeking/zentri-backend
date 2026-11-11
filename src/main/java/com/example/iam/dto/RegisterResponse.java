package com.example.iam.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {

    private final Long idUsuario;
    private final String username;

    public RegisterResponse(Long idUsuario, String username) {
        this.idUsuario = idUsuario;
        this.username = username;
    }
}
