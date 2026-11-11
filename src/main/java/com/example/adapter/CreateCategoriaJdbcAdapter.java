package com.example.adapter;

import com.example.model.Categoria;
import com.example.port.CreateCategoriaPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class CreateCategoriaJdbcAdapter implements CreateCategoriaPort {

    private final JdbcTemplate jdbcTemplate;

    public CreateCategoriaJdbcAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean existsByNombre(String nombre, Long idUsuario) {
        String sql = "select count(1) " +
                "from t_m_categoria " +
                "where id_usuario = ? " +
                "and upper(nombre) = upper(?)";
        return jdbcTemplate.queryForObject(sql, Integer.class, idUsuario, nombre) > 0;
    }

    @Override
    public Categoria save(Categoria categoria) {
        String sql = "insert into t_m_categoria(id_usuario, nombre, fec_cre) " +
                "values (?, ?, sysdate)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id_categoria"});
            ps.setLong(1, categoria.getUsuario().getIdUsuario());
            ps.setString(2, categoria.getNombre());
            return ps;
        }, keyHolder);
        categoria.setIdCategoria(keyHolder.getKey().longValue());
        return categoria;
    }
}
