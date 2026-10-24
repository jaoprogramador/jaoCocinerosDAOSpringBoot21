package com.jao.cocineros.dao;


import com.jao.cocineros.dto.CocinerosRequest;
import com.jao.cocineros.dto.CocinerosResponse;
import com.jao.cocineros.model.Cocineros;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CocinerosDAOImpl implements CocinerosDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)

    public List<CocinerosResponse> findAll() {
        String jpql = """
                SELECT new com.jao.cocineros.dto.CocinerosResponse(
                    c.id, c.nombre, c.especialidad, c.anosExperiencia, c.activo)
                    FROM Cocineros c
                """;

        return this.entityManager.createQuery(jpql, CocinerosResponse.class)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public CocinerosResponse findById(Long id) {
        String jpql = """
                SELECT new com.example.cocineros.presentation.dto.CocinerosResponse(
                    c.id, c.nombre, c.especialidad, c.anosExperiencia, c.activo)
                    FROM Cocineros c
                    WHERE c.id = :id
                """;

        try {
            return this.entityManager.createQuery(jpql, CocinerosResponse.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void save(CocinerosRequest request) {
        Cocineros cocinero = new Cocineros(request); // Asegúrate de tener este constructor en tu Entity
        this.entityManager.persist(cocinero);
    }

    @Override
    @Transactional
    public boolean update(Long id, CocinerosRequest request) {
        Cocineros cocinero = this.entityManager.find(Cocineros.class, id);
        if (cocinero == null) {
            return false;
        }

        cocinero.setNombre(request.getNombre());
        cocinero.setEspecialidad(request.getEspecialidad());
        cocinero.setAnosExperiencia(request.getAnosExperiencia());
        cocinero.setActivo(request.getActivo());

        this.entityManager.merge(cocinero);

        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        Cocineros cocinero = this.entityManager.find(Cocineros.class, id);
        if (cocinero == null) {
            return false;
        }

        this.entityManager.remove(cocinero);

        return true;
    }
}

