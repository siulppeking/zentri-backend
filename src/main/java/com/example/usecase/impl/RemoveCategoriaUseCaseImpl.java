package com.example.usecase.impl;

import com.example.command.RemoveCategoriaCommand;
import com.example.exception.BusinessRuleException;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Categoria;
import com.example.port.RemoveCategoriaPort;
import com.example.usecase.RemoveCategoriaUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RemoveCategoriaUseCaseImpl implements RemoveCategoriaUseCase {

    private final RemoveCategoriaPort removeCategoriaPort;

    public RemoveCategoriaUseCaseImpl(RemoveCategoriaPort removeCategoriaPort) {
        this.removeCategoriaPort = removeCategoriaPort;
    }

    @Override
    @Transactional
    public void execute(RemoveCategoriaCommand command) {
        Categoria categoria = removeCategoriaPort.findById(command.getId(), command.getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria con id '" + command.getId() + "' no encontrada"));

        if (removeCategoriaPort.existsMovimientosByCategoria_Id(command.getId(), command.getIdUsuario())) {
            throw new BusinessRuleException("No se puede eliminar la categoria '" + categoria.getNombre() + "' porque tiene movimientos asociados");
        }
        removeCategoriaPort.deleteById(command.getId(), command.getIdUsuario());
    }
}
