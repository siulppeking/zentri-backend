package com.example.usecase.impl;

import com.example.command.ModifyCategoriaCommand;
import com.example.dto.response.ModifyCategoriaResponse;
import com.example.exception.BusinessRuleException;
import com.example.exception.ResourceNotFoundException;
import com.example.iam.model.Usuario;
import com.example.model.Categoria;
import com.example.port.ModifyCategoriaPort;
import com.example.usecase.ModifyCategoriaUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ModifyCategoriaUseCaseImpl implements ModifyCategoriaUseCase {

    private final ModifyCategoriaPort modifyCategoriaPort;

    public ModifyCategoriaUseCaseImpl(ModifyCategoriaPort modifyCategoriaPort) {
        this.modifyCategoriaPort = modifyCategoriaPort;
    }

    @Override
    @Transactional
    public ModifyCategoriaResponse execute(ModifyCategoriaCommand command) {
        modifyCategoriaPort.findById(command.getId(), command.getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria con id '" + command.getId() + "' no encontrada"));

        if (modifyCategoriaPort.existsByNombreAndIdNot(command.getNombre(), command.getId(), command.getIdUsuario())) {
            throw new BusinessRuleException("Categoria con nombre '" + command.getNombre() + "' ya existe");
        }
        Categoria categoriaToSave = new Categoria();
        categoriaToSave.setIdCategoria(command.getId());
        categoriaToSave.setNombre(command.getNombre());
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(command.getIdUsuario());
        categoriaToSave.setUsuario(usuario);

        Categoria categoria = modifyCategoriaPort.update(categoriaToSave);

        return new ModifyCategoriaResponse(
                categoria.getIdCategoria(),
                categoria.getNombre()
        );
    }
}
