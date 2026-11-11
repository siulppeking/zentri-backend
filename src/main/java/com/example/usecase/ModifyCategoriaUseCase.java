package com.example.usecase;

import com.example.command.ModifyCategoriaCommand;
import com.example.dto.response.ModifyCategoriaResponse;

public interface ModifyCategoriaUseCase {

    ModifyCategoriaResponse execute(ModifyCategoriaCommand command);

}
