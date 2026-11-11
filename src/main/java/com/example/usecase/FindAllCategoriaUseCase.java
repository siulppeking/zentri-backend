package com.example.usecase;

import com.example.dto.response.ListarCategoriaResponse;
import com.example.query.ListarCategoriaQuery;

import java.util.List;

public interface FindAllCategoriaUseCase {

    List<ListarCategoriaResponse> execute(ListarCategoriaQuery query);

}
