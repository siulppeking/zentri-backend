package com.example.usecase.impl;

import com.example.dto.response.FindCategoriaByIdResponse;
import com.example.exception.ResourceNotFoundException;
import com.example.port.FindCategoriaByIdPort;
import com.example.query.FindCategoriaQuery;
import com.example.usecase.FindCategoriaByIdUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FindCategoriaByIdUseCaseImpl implements FindCategoriaByIdUseCase {

    private final FindCategoriaByIdPort findCategoriaByIdPort;

    public FindCategoriaByIdUseCaseImpl(FindCategoriaByIdPort findCategoriaByIdPort) {
        this.findCategoriaByIdPort = findCategoriaByIdPort;
    }

    @Override
    @Transactional(readOnly = true)
    public FindCategoriaByIdResponse execute(FindCategoriaQuery query) {
        return findCategoriaByIdPort.findById(
                query.getIdCategoria(),
                query.getIdUsuario()
        ).map(categoria -> new FindCategoriaByIdResponse(
                categoria.getIdCategoria(),
                categoria.getNombre())
        ).orElseThrow(() -> new ResourceNotFoundException("Categoria con id '" + query.getIdCategoria() + "' no encontrada"));
    }

}
