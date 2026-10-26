package com.jao.cocineros.dao;


import com.jao.cocineros.model.dto.CocinerosRequest;
import com.jao.cocineros.model.dto.CocinerosResponse;
import com.jao.cocineros.model.entity.Cocineros;

import java.util.List;


public interface CocinerosDAO {
    List<Cocineros> findAll();

    Cocineros findById(Long id);

    void save(Cocineros cocineros);

    boolean update(Long id, Cocineros cocineros);

    boolean delete(Long id);
}