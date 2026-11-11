package com.example.adapter;

import com.example.model.Categoria;
import com.example.port.RemoveCategoriaPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RemoveCategoriaJdbcAdapter implements RemoveCategoriaPort {

    private final JdbcTemplate jdbcTemplate;

    public RemoveCategoriaJdbcAdapter(JdbcTemplate jdbcTemplate) {
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

    @Override
    public boolean existsMovimientosByCategoria_Id(Long id, Long idUsuario) {
        String sql = "SELECT count(1) " +
                "FROM T_T_MOVIMIENTO " +
                "WHERE ID_USUARIO = ? " +
                "AND ID_CATEGORIA = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, idUsuario, id) > 0;
    }

    @Override
    public void deleteById(Long id, Long idUsuario) {
        String sql = "delete from t_m_categoria " +
                "where id_usuario = ? " +
                "and id_categoria = ?";
        jdbcTemplate.update(sql, idUsuario, id);
    }
}
