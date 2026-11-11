package com.example.command;

import lombok.Getter;
import com.example.exception.CommandValidationException;

@Getter
public class CreateCategoriaCommand {

    private final Long idUsuario;
    private final String nombre;

    public CreateCategoriaCommand(
            Long idUsuario,
            String nombre) {
        if (idUsuario == null) {
            throw new CommandValidationException("IdUsuario no puede ser nulo");
        }
        if (idUsuario <= 0) {
            throw new CommandValidationException("IdUsuario debe ser mayor que cero");
        }
        if (nombre == null) {
            throw new CommandValidationException("Nombre no puede ser nulo");
        }
        if (nombre.trim().isEmpty()) {
            throw new CommandValidationException("Nombre no puede estar vacío");
        }
        if (nombre.trim().length() < 3 || nombre.trim().length() > 20) {
            throw new CommandValidationException("Nombre debe tener entre 3 y 20 caracteres");
        }
        this.idUsuario = idUsuario;
        this.nombre = nombre.trim();
    }
}
