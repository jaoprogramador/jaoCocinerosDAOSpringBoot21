package com.jao.cocineros.service;

import com.jao.cocineros.model.dto.CocinerosRequest;
import com.jao.cocineros.model.dto.CocinerosResponse;

import java.util.List;
import java.util.Optional;

public interface ICocinerosService {

    List<CocinerosResponse> findAll();

    Optional<CocinerosResponse> findById(Long id);

    CocinerosResponse save(CocinerosRequest request);

    boolean update(Long id, CocinerosRequest request);

    boolean delete(Long id);
}