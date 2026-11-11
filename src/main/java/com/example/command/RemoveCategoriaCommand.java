package com.example.command;

import com.example.exception.CommandValidationException;
import lombok.Getter;

@Getter
public class RemoveCategoriaCommand {

    private final Long id;
    private final Long idUsuario;

    public RemoveCategoriaCommand(
            Long id,
            Long idUsuario) {
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
        this.id = id;
        this.idUsuario = idUsuario;
    }
}
