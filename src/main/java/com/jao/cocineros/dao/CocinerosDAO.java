package com.jao.cocineros.dao;


import com.jao.cocineros.dto.CocinerosRequest;
import com.jao.cocineros.dto.CocinerosResponse;
import com.jao.cocineros.model.Cocineros;

import java.util.List;
import java.util.Optional;


public interface CocinerosDAO {
    List<CocinerosResponse> findAll();
    CocinerosResponse findById(Long id);
    void save(CocinerosRequest request);
    boolean update(Long codigo, CocinerosRequest request);
    boolean delete(Long id);
}