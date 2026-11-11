package com.example.iam.controller;

import com.example.iam.dto.UserInfo;
import com.example.iam.model.Rol;
import com.example.iam.model.Usuario;
import com.example.iam.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class InfoRestController {

    private final MenuService menuService;

    public InfoRestController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/info")
    public ResponseEntity<UserInfo> getUserInfo() {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = new UserInfo(
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getUsername(),
                usuario.getRoles()
                        .stream()
                        .map(Rol::getNombre)
                        .collect(Collectors.toList()),
                menuService.listarMenusPorUsuario(usuario.getIdUsuario())
        );
        return ResponseEntity.ok(userInfo);
    }
}
