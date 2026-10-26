package com.jao.cocineros.dao;


import com.jao.cocineros.model.dto.CocinerosRequest;
import com.jao.cocineros.model.dto.CocinerosResponse;
import com.jao.cocineros.model.entity.Cocineros;

import com.jao.cocineros.model.entity.DatosEscuela;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class CocinerosDAOImpl implements CocinerosDAO {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Cocineros> findAll() {
        //String sql = "SELECT * FROM cocineros";
        // Devuelve la lista pura de Entidades de Base de Datos
        //return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cocineros.class));
        String sql = "SELECT c.id AS cocinero_id, c.nombre AS cocinero_nombre, c.especialidad, c.anos_experiencia, " +
                "c.email, c.telefono, c.fecha_nacimiento, c.activo, " +
                "e.id AS escuela_id, e.nombre AS escuela_nombre, e.direccion, e.ciudad " +
                "FROM cocineros c " +
                "LEFT JOIN datos_escuela e ON c.id = e.cocinero_id";

        return this.jdbcTemplate.query(sql, (rs, rowNum) -> {
            // 1. Mapear el Cocinero
            Cocineros cocinero = new Cocineros();
            cocinero.setId(rs.getLong("cocinero_id"));
            cocinero.setNombre(rs.getString("cocinero_nombre"));
            cocinero.setEspecialidad(rs.getString("especialidad"));
            cocinero.setAnosExperiencia(rs.getObject("anos_experiencia") != null ? rs.getInt("anos_experiencia") : null);
            cocinero.setEmail(rs.getString("email"));
            cocinero.setTelefono(rs.getString("telefono"));

            String fechaNac = rs.getString("fecha_nacimiento");
            cocinero.setFechaNacimiento(fechaNac);

            cocinero.setActivo(rs.getObject("activo") != null ? rs.getBoolean("activo") : null);

            // 2. Mapear los Datos de la Escuela (siempre que tenga ID en la tabla asociada)
            Long escuelaId = rs.getLong("escuela_id");
            if (!rs.wasNull()) {
                DatosEscuela datosEscuela = new DatosEscuela();
                datosEscuela.setId(escuelaId);
                datosEscuela.setNombre(rs.getString("escuela_nombre"));
                datosEscuela.setDireccion(rs.getString("direccion"));
                datosEscuela.setCiudad(rs.getString("ciudad"));

                cocinero.setDatosEscuela(datosEscuela);
            }

            return cocinero;
        });
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
        //String sql = "INSERT INTO cocineros (nombre, especialidad, anos_experiencia, activo) VALUES (?, ?, ?, ?)";
        //this.jdbcTemplate.update(sql,cocineros.getNombre(),cocineros.getEspecialidad(),cocineros.getAnosExperiencia(),cocineros.getActivo());
        String sqlCocinero = "INSERT INTO cocineros (nombre, especialidad, anos_experiencia, email, telefono, fecha_nacimiento, activo) VALUES (?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            //PreparedStatement ps = connection.prepareStatement(sqlCocinero, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement ps = connection.prepareStatement(sqlCocinero, new String[] { "id" });
            ps.setString(1, cocineros.getNombre());
            ps.setString(2, cocineros.getEspecialidad());
            ps.setInt(3, cocineros.getAnosExperiencia());
            ps.setString(4, cocineros.getEmail());
            ps.setString(5, cocineros.getTelefono());
            ps.setString(6, cocineros.getFechaNacimiento());
            ps.setBoolean(7, cocineros.getActivo());
            return ps;
        }, keyHolder);

        // Obtenemos el ID generado para el cocinero
        Long cocineroId = keyHolder.getKey().longValue();
        cocineros.setId(cocineroId);

        // Si tiene datos de escuela, los guardamos relacionándolos con el ID del cocinero
        if (cocineros.getDatosEscuela() != null) {
            String sqlEscuela = "INSERT INTO datos_escuela (nombre, direccion, ciudad, cocinero_id) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sqlEscuela,
                    cocineros.getDatosEscuela().getNombre(),
                    cocineros.getDatosEscuela().getDireccion(),
                    cocineros.getDatosEscuela().getCiudad(),
                    cocineroId
            );
        }
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

