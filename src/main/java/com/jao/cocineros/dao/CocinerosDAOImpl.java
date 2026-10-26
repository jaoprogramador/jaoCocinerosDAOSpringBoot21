package com.jao.cocineros.dao;


import com.jao.cocineros.model.dto.CocinerosRequest;
import com.jao.cocineros.model.dto.CocinerosResponse;
import com.jao.cocineros.model.entity.Cocineros;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class CocinerosDAOImpl implements CocinerosDAO {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Cocineros> findAll() {
        String sql = "SELECT * FROM cocineros";
        // Devuelve la lista pura de Entidades de Base de Datos
        return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cocineros.class));
    }

    @Override
    public Cocineros findById(Long id) {
        try {
            String sql = "SELECT * FROM cocineros WHERE id = ?";
            // Devuelve la Entidad directamente (o lanza excepción si no existe)
            return this.jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Cocineros.class), id);
        } catch (Exception e) {
            return null; // O puedes manejarlo con Optional desde el DAO si prefieres
        }
    }

    @Override
    public void save(Cocineros cocineros) {
        String sql = "INSERT INTO cocineros (nombre, especialidad, anos_experiencia, activo) VALUES (?, ?, ?, ?)";
        this.jdbcTemplate.update(sql,
                cocineros.getNombre(),
                cocineros.getEspecialidad(),
                cocineros.getAnosExperiencia(),
                cocineros.getActivo()
        );
    }

    @Override
    public boolean update(Long id, Cocineros cocineros) {
        // Recibe la entidad ya mapeada, no el Request
        String sql = "UPDATE cocineros SET nombre = ?, especialidad = ?, anos_experiencia = ?, activo = ? WHERE id = ?";
        int result = this.jdbcTemplate.update(sql,
                cocineros.getNombre(),
                cocineros.getEspecialidad(),
                cocineros.getAnosExperiencia(),
                cocineros.getActivo(),
                id
        );

        return (result == 1);
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM cocineros WHERE id = ?";
        int result = this.jdbcTemplate.update(sql, id);

        return (result == 1);
    }
}

