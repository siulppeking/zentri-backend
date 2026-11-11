package com.example.controller;

import com.example.command.ModifyCategoriaCommand;
import com.example.command.RemoveCategoriaCommand;
import com.example.dto.request.ModifyCategoriaRequest;
import com.example.dto.response.FindCategoriaByIdResponse;
import com.example.dto.response.ModifyCategoriaResponse;
import com.example.query.FindCategoriaQuery;
import com.example.usecase.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.command.CreateCategoriaCommand;
import com.example.dto.request.CreateCategoriaRequest;
import com.example.dto.response.CreateCategoriaResponse;
import com.example.dto.response.ListarCategoriaResponse;
import com.example.iam.model.Usuario;
import com.example.query.ListarCategoriaQuery;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaRestController {

    private final FindAllCategoriaUseCase findAllCategoriaUseCase;
    private final CreateCategoriaUseCase createCategoriaUseCase;
    private final FindCategoriaByIdUseCase findCategoriaByIdUseCase;
    private final ModifyCategoriaUseCase modifyCategoriaUseCase;
    private final RemoveCategoriaUseCase removeCategoriaUseCase;

    public CategoriaRestController(FindAllCategoriaUseCase findAllCategoriaUseCase,
                                   CreateCategoriaUseCase createCategoriaUseCase,
                                   FindCategoriaByIdUseCase findCategoriaByIdUseCase,
                                   ModifyCategoriaUseCase modifyCategoriaUseCase,
                                   RemoveCategoriaUseCase removeCategoriaUseCase) {
        this.findAllCategoriaUseCase = findAllCategoriaUseCase;
        this.createCategoriaUseCase = createCategoriaUseCase;
        this.findCategoriaByIdUseCase = findCategoriaByIdUseCase;
        this.modifyCategoriaUseCase = modifyCategoriaUseCase;
        this.removeCategoriaUseCase = removeCategoriaUseCase;
    }

    @GetMapping
    public ResponseEntity<List<ListarCategoriaResponse>> get() {
        Long idUsuario = getIdUsuarioFromSecurityContext();
        ListarCategoriaQuery query = new ListarCategoriaQuery(
                idUsuario
        );
        return ResponseEntity.ok(findAllCategoriaUseCase.execute(query));
    }

    @PostMapping
    public ResponseEntity<CreateCategoriaResponse> create(@Valid @RequestBody CreateCategoriaRequest request) {
        Long idUsuario = getIdUsuarioFromSecurityContext();
        CreateCategoriaCommand command = new CreateCategoriaCommand(
                idUsuario,
                request.getNombre()
        );
        CreateCategoriaResponse categoria = createCategoriaUseCase.execute(command);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.getId())
                .toUri();
        return ResponseEntity.created(location).body(categoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindCategoriaByIdResponse> findById(@PathVariable Long id) {
        Long idUsuario = getIdUsuarioFromSecurityContext();
        FindCategoriaQuery query = new FindCategoriaQuery(
                id,
                idUsuario
        );
        return ResponseEntity.ok(findCategoriaByIdUseCase.execute(query));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModifyCategoriaResponse> update(@PathVariable Long id,
                                                          @Valid @RequestBody ModifyCategoriaRequest request) {
        Long idUsuario = getIdUsuarioFromSecurityContext();
        ModifyCategoriaCommand command = new ModifyCategoriaCommand(
                id,
                idUsuario,
                request.getNombre()
        );
        ModifyCategoriaResponse categoria = modifyCategoriaUseCase.execute(command);
        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Long idUsuario = getIdUsuarioFromSecurityContext();
        RemoveCategoriaCommand command = new RemoveCategoriaCommand(
                id,
                idUsuario
        );
        removeCategoriaUseCase.execute(command);
        return ResponseEntity.noContent().build();
    }

    private Long getIdUsuarioFromSecurityContext() {
        return ((Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIdUsuario();
    }

}