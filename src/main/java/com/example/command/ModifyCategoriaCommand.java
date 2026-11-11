package com.example.command;

import com.example.exception.CommandValidationException;
import lombok.Getter;

@Getter
public class ModifyCategoriaCommand {

    private final Long id;
    private final Long idUsuario;
    private final String nombre;

    public ModifyCategoriaCommand(
            Long id,
            Long idUsuario,
            String nombre) {
        if (id == null) {
            throw new CommandValidationException("IdCategoria no puede ser nulo");
        }
        if (id <= 0) {
            throw new CommandValidationException("IdCategoria debe ser mayor que cero");
        }
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
        this.id = id;
        this.idUsuario = idUsuario;
        this.nombre = nombre.trim();
    }
}
