package com.example.adapter;

import com.example.model.Categoria;
import com.example.port.FindAllCategoriaPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FindAllCategoriaJdbcAdapter implements FindAllCategoriaPort {

    private final JdbcTemplate jdbcTemplate;

    public FindAllCategoriaJdbcAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Categoria> findAll(Long idUsuario) {
        String sql = "select id_categoria, nombre " +
                "from t_m_categoria " +
                "where id_usuario = ? " +
                "order by id_categoria desc";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(rs.getLong("id_categoria"));
            categoria.setNombre(rs.getString("nombre"));
            return categoria;
        }, idUsuario);
    }
}
