package com.example.iam.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.iam.model.Rol;
import com.example.iam.repository.RolRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class RolRepositoryImpl implements RolRepository {

    private final JdbcTemplate jdbcTemplate;

    public RolRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Rol> findAllByIdUsuario(Long idUsuario) {
        String sql = "select c.id_rol, c.nombre " +
                "from seg_usuario_rol a " +
                "inner join seg_usuario b on (b.id_usuario=a.id_usuario) " +
                "inner join seg_rol c on (c.id_rol=a.id_rol) " +
                "where a.id_usuario = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Rol rol = new Rol();
            rol.setIdRol(rs.getLong("id_rol"));
            rol.setNombre(rs.getString("nombre"));
            return rol;
        }, idUsuario);
    }

    @Override
    public Optional<Rol> findByNombre(String nombre) {
        String sql = "select id_rol, nombre " +
                "from seg_rol " +
                "where nombre = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Rol rol = new Rol();
            rol.setIdRol(rs.getLong("id_rol"));
            rol.setNombre(rs.getString("nombre"));
            return rol;
        }, nombre).stream().findFirst();
    }
}
