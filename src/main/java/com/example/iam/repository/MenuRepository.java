package com.example.iam.repository;


import com.example.iam.model.Menu;

import java.util.List;

public interface MenuRepository {

    List<Menu> findAllByIdUsuario(Long idUsuario);

}
