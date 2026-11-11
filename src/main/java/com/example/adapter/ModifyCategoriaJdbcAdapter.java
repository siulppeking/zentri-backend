package com.example.adapter;

import com.example.model.Categoria;
import com.example.port.ModifyCategoriaPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Optional;

@Repository
public class ModifyCategoriaJdbcAdapter implements ModifyCategoriaPort {

    private final JdbcTemplate jdbcTemplate;

    public ModifyCategoriaJdbcAdapter(JdbcTemplate jdbcTemplate) {
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
    public boolean existsByNombreAndIdNot(String nombre, Long id, Long idUsuario) {
        String sql = "select count(1) " +
                "from t_m_categoria " +
                "where id_usuario = ? " +
                "and upper(nombre) = upper(?) " +
                "and id_categoria <> ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, idUsuario, nombre, id) > 0;
    }

    @Override
    public Categoria update(Categoria categoria) {
        String sql = "update t_m_categoria " +
                "set nombre = ?, " +
                "    fec_mod = sysdate " +
                "where id_usuario = ? " +
                "and id_categoria = ?";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, categoria.getNombre());
            ps.setLong(2, categoria.getUsuario().getIdUsuario());
            ps.setLong(3, categoria.getIdCategoria());
            return ps;
        });
        return categoria;
    }
}
