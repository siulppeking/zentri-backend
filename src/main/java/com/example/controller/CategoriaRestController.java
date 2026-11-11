package com.example.controller;

import com.example.dto.response.FindCategoriaByIdResponse;
import com.example.query.FindCategoriaQuery;
import com.example.usecase.FindCategoriaByIdUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.command.CreateCategoriaCommand;
import com.example.dto.request.CreateCategoriaRequest;
import com.example.dto.response.CreateCategoriaResponse;
import com.example.dto.response.ListarCategoriaResponse;
import com.example.iam.model.Usuario;
import com.example.query.ListarCategoriaQuery;
import com.example.usecase.CreateCategoriaUseCase;
import com.example.usecase.FindAllCategoriaUseCase;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaRestController {

    private final FindAllCategoriaUseCase findAllCategoriaUseCase;
    private final CreateCategoriaUseCase createCategoriaUseCase;
    private final FindCategoriaByIdUseCase findCategoriaByIdUseCase;

    public CategoriaRestController(FindAllCategoriaUseCase findAllCategoriaUseCase,
                                   CreateCategoriaUseCase createCategoriaUseCase,
                                   FindCategoriaByIdUseCase findCategoriaByIdUseCase) {
        this.findAllCategoriaUseCase = findAllCategoriaUseCase;
        this.createCategoriaUseCase = createCategoriaUseCase;
        this.findCategoriaByIdUseCase = findCategoriaByIdUseCase;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ListarCategoriaResponse>> get() {
        Long idUsuario = getIdUsuarioFromSecurityContext();
        ListarCategoriaQuery query = new ListarCategoriaQuery(
                idUsuario
        );
        return ResponseEntity.ok(findAllCategoriaUseCase.execute(query));
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
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
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<FindCategoriaByIdResponse> findById(@PathVariable Long id) {
        Long idUsuario = getIdUsuarioFromSecurityContext();
        FindCategoriaQuery query = new FindCategoriaQuery(
                id,
                idUsuario
        );
        return ResponseEntity.ok(findCategoriaByIdUseCase.execute(query));
    }


    private Long getIdUsuarioFromSecurityContext() {
        return ((Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIdUsuario();
    }

}
