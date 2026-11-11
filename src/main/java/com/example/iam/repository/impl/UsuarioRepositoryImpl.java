package com.example.iam.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.example.iam.model.Usuario;
import com.example.iam.repository.UsuarioRepository;

import java.sql.PreparedStatement;
import java.util.Optional;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        String sql = "select id_usuario, nombre_usuario, contrasena, nombre, apellido, habilitado " +
                "from seg_usuario " +
                "where nombre_usuario = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getLong("id_usuario"));
            usuario.setNombreUsuario(rs.getString("nombre_usuario"));
            usuario.setContrasena(rs.getString("contrasena"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellido(rs.getString("apellido"));
            usuario.setHabilitado(rs.getInt("habilitado") == 1);
            return usuario;
        }, username).stream().findFirst();
    }

    @Override
    public boolean existsByUsername(String username) {
        String sql = "select count(1) cantidad " +
                "from seg_usuario " +
                "where upper(nombre_usuario) = upper(?)";
        return jdbcTemplate.queryForObject(sql, Integer.class, username) > 0;
    }

    @Override
    public Usuario save(Usuario usuario) {
        String sql = "insert into seg_usuario(nombre_usuario, contrasena, nombre, apellido, habilitado, fec_cre) " +
                "values (?, ?, ?, ?, '1', sysdate)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id_usuario"});
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getContrasena());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getApellido());
            return ps;
        }, keyHolder);
        usuario.setIdUsuario(keyHolder.getKey().longValue());
        return usuario;
    }

    @Override
    public boolean saveDetailUsuarioRol(Long idUsuario, Long idRol) {
        String sql = "insert into seg_usuario_rol(id_usuario, id_rol) " +
                "values (?, ?)";
        return jdbcTemplate.update(sql, idUsuario, idRol) > 0;
    }
}
