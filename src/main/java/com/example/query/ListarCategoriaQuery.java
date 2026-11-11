package com.example.query;

import lombok.Getter;
import com.example.exception.QueryValidationException;

@Getter
public class ListarCategoriaQuery {

    private final Long idUsuario;

    public ListarCategoriaQuery(Long idUsuario) {
        if (idUsuario == null) {
            throw new QueryValidationException("IdUsuario no puede ser nulo");
        }
        if (idUsuario <= 0) {
            throw new QueryValidationException("IdUsuario debe ser mayor que cero");
        }
        this.idUsuario = idUsuario;
    }

}
