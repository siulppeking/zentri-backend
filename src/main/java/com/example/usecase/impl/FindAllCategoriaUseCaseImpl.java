package com.example.usecase.impl;

import org.springframework.stereotype.Service;
import com.example.dto.response.ListarCategoriaResponse;
import com.example.port.FindAllCategoriaPort;
import com.example.query.ListarCategoriaQuery;
import com.example.usecase.FindAllCategoriaUseCase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAllCategoriaUseCaseImpl implements FindAllCategoriaUseCase {

    private final FindAllCategoriaPort findAllCategoriaPort;

    public FindAllCategoriaUseCaseImpl(FindAllCategoriaPort findAllCategoriaPort) {
        this.findAllCategoriaPort = findAllCategoriaPort;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ListarCategoriaResponse> execute(ListarCategoriaQuery query) {
        return findAllCategoriaPort.findAll(query.getIdUsuario()).stream()
                .map(categoria -> new ListarCategoriaResponse(
                        categoria.getIdCategoria(),
                        categoria.getNombre()
                )).collect(Collectors.toList());
    }

}
