package com.example.iam.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterRequest {

    @NotNull(message = "Nombre no puede ser null")
    @NotBlank(message = "Nombre no puede ser vacio")
    @Size(min = 3, max = 50, message = "Nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @NotNull(message = "Apellido no puede ser null")
    @NotBlank(message = "Apellido no puede ser vacio")
    @Size(min = 3, max = 50, message = "Apellido debe tener entre 3 y 50 caracteres")
    private String apellido;

    @NotNull(message = "Username no puede ser null")
    @NotBlank(message = "Username no puede ser vacio")
    @Size(min = 3, max = 50, message = "Username debe tener entre 3 y 50 caracteres")
    private String username;

    @NotNull(message = "Password no puede ser null")
    @NotBlank(message = "Password no puede ser vacio")
    @Size(min = 6, max = 20, message = "Password debe tener entre 6 y 20 caracteres")
    private String password;

}
