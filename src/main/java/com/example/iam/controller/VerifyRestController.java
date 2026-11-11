package com.example.iam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.iam.model.Menu;
import com.example.iam.model.Usuario;
import com.example.iam.service.MenuService;

import java.util.List;

@RestController
public class VerifyRestController {

    private final MenuService menuService;

    public VerifyRestController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/verify")
    public ResponseEntity<List<Menu>> verify() {
        Long idUsuario = ((Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIdUsuario();
        return ResponseEntity.ok(menuService.listarMenusPorUsuario(idUsuario));
    }

}
