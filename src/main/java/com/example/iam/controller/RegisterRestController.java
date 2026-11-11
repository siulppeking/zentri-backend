package com.example.iam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.iam.dto.RegisterRequest;
import com.example.iam.dto.RegisterResponse;
import com.example.iam.service.UsuarioService;

import javax.validation.Valid;

@RestController
public class RegisterRestController {

    private final UsuarioService usuarioService;

    public RegisterRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(usuarioService.register(registerRequest));
    }
}
