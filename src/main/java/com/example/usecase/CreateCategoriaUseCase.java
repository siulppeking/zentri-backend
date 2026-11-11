package com.example.usecase;

import com.example.command.CreateCategoriaCommand;
import com.example.dto.response.CreateCategoriaResponse;

public interface CreateCategoriaUseCase {

    CreateCategoriaResponse execute(CreateCategoriaCommand command);

}
