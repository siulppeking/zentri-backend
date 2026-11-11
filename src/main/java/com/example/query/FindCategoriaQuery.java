package com.example.query;

import com.example.exception.QueryValidationException;
import lombok.Getter;

@Getter
public class FindCategoriaQuery {

    private final Long idCategoria;
    private final Long idUsuario;

    public FindCategoriaQuery(
            Long idCategoria,
            Long idUsuario) {
        if (idCategoria == null) {
            throw new QueryValidationException("Id de categoria no puede ser null");
        }
        if (idCategoria <= 0) {
            throw new QueryValidationException("Id de categoria debe ser un número positivo");
        }
        if (idUsuario == null) {
            throw new QueryValidationException("Id de usuario no puede ser null");
        }
        if (idUsuario <= 0) {
            throw new QueryValidationException("Id de usuario debe ser un número positivo");
        }
        this.idCategoria = idCategoria;
        this.idUsuario = idUsuario;
    }
}
