package com.example.iam.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.iam.model.Menu;
import com.example.iam.repository.MenuRepository;

import java.util.List;

@Repository
public class MenuRepositoryImpl implements MenuRepository {

    private final JdbcTemplate jdbcTemplate;

    public MenuRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Menu> findAllByIdUsuario(Long idUsuario) {
        String sql = "select distinct c.id_menu, c.nombre, c.icono, c.ruta " +
                "from seg_rol_menu a " +
                "inner join seg_usuario_rol b on (b.id_rol=a.id_rol) " +
                "inner join seg_menu c on (c.id_menu=a.id_menu) " +
                "inner join seg_usuario d on (d.id_usuario=b.id_usuario) " +
                "where d.id_usuario = ? " +
                "order by c.id_menu";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Menu menu = new Menu();
            menu.setIdMenu(rs.getLong("id_menu"));
            menu.setNombre(rs.getString("nombre"));
            menu.setIcono(rs.getString("icono"));
            menu.setUrl(rs.getString("ruta"));
            return menu;
        }, idUsuario);
    }
}
