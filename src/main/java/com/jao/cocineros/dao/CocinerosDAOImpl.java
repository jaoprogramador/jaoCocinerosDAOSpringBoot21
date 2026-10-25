package com.jao.cocineros.dao;


import com.jao.cocineros.dto.CocinerosRequest;
import com.jao.cocineros.dto.CocinerosResponse;
import com.jao.cocineros.model.Cocineros;

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
    public List<CocinerosResponse> findAll() {
        String sql = "SELECT * FROM cocineros";
        List<Cocineros> cocineros = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cocineros.class));
        return cocineros.stream()
                .map(CocinerosResponse::new)
                .toList();


    }

    @Override
    public CocinerosResponse findById(Long id) {
        Cocineros cocinero;

        try {
            String sql = "SELECT * FROM cocineros WHERE id = ?";
            cocinero = this.jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Cocineros.class), id);
        } catch (Exception e) {
            return null;
        }
        if (cocinero != null) {
            return new CocinerosResponse(cocinero);
        }
        return null;

    }

    @Override
    public void save(CocinerosRequest request) {
        String sql = "INSERT INTO cocineros (nombre, especialidad, anos_experiencia, activo) VALUES (?, ?, ? , ?)";
        this.jdbcTemplate.update(sql, request.getNombre(), request.getEspecialidad(),request.getAnosExperiencia(),request.getActivo());
    }

    @Override
    public boolean update(Long codigo, CocinerosRequest request) {
        String sql = "UPDATE cocineros SET nombre = ?, especialidad = ? WHERE id = ?";
        int result = this.jdbcTemplate.update(sql, request.getNombre(), request.getEspecialidad(), codigo);

        return (result == 1);
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM cocineros WHERE id = ?";
        int result = this.jdbcTemplate.update(sql, id);

        return (result == 1);
    }
}

