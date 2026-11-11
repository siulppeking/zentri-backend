package com.example.iam.service;

import org.springframework.stereotype.Service;
import com.example.iam.model.Menu;
import com.example.iam.repository.MenuRepository;

import java.util.List;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    // listar Menus por Usuario
    public List<Menu> listarMenusPorUsuario(Long idUsuario) {
        return menuRepository.findAllByIdUsuario(idUsuario);
    }

}
