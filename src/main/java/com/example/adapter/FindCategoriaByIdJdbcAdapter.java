package com.example.adapter;

import com.example.model.Categoria;
import com.example.port.FindCategoriaByIdPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FindCategoriaByIdJdbcAdapter implements FindCategoriaByIdPort {

    private final JdbcTemplate jdbcTemplate;

    public FindCategoriaByIdJdbcAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Categoria> findById(Long id, Long idUsuario) {
        String sql = "select id_categoria, nombre " +
                "from t_m_categoria " +
                "where id_usuario = ? " +
                "and id_categoria = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(rs.getLong("id_categoria"));
            categoria.setNombre(rs.getString("nombre"));
            return categoria;
        }, idUsuario, id).stream().findFirst();
    }
}
