package com.example.usecase;

import com.example.dto.response.FindCategoriaByIdResponse;
import com.example.query.FindCategoriaQuery;

public interface FindCategoriaByIdUseCase {

    FindCategoriaByIdResponse execute(FindCategoriaQuery query);

}
