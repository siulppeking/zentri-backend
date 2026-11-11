package com.example.iam.dto;

import com.example.iam.model.Menu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserInfo {

    private final String nombre;
    private final String apellido;
    private final String username;
    private final List<String> roles;
    private final List<Menu> menus;

    public UserInfo(
            String nombre,
            String apellido,
            String username,
            List<String> roles,
            List<Menu> menus) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.roles = roles;
        this.menus = menus;
    }
}
