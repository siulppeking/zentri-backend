package com.example.usecase.impl;

import org.springframework.stereotype.Service;
import com.example.command.CreateCategoriaCommand;
import com.example.dto.response.CreateCategoriaResponse;
import com.example.exception.BusinessRuleException;
import com.example.iam.model.Usuario;
import com.example.model.Categoria;
import com.example.port.CreateCategoriaPort;
import com.example.usecase.CreateCategoriaUseCase;

@Service
public class CreateCategoriaUseCaseImpl implements CreateCategoriaUseCase {

    private final CreateCategoriaPort createCategoriaPort;

    public CreateCategoriaUseCaseImpl(CreateCategoriaPort createCategoriaPort) {
        this.createCategoriaPort = createCategoriaPort;
    }

    @Override
    public CreateCategoriaResponse execute(CreateCategoriaCommand command) {
        if (createCategoriaPort.existsByNombre(command.getNombre(), command.getIdUsuario())) {
            throw new BusinessRuleException("La categoria '" + command.getNombre() + "' ya se encuentra registrada.");
        }
        Categoria categoriaToSave = new Categoria();
        categoriaToSave.setNombre(command.getNombre());
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(command.getIdUsuario());
        categoriaToSave.setUsuario(usuario);

        Categoria categoria = createCategoriaPort.save(categoriaToSave);

        return new CreateCategoriaResponse(
                categoria.getIdCategoria(),
                categoria.getNombre()
        );
    }
}
