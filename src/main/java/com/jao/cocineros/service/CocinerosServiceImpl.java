package com.jao.cocineros.service;

import com.jao.cocineros.dao.CocinerosDAO;
import com.jao.cocineros.dto.CocinerosRequest;
import com.jao.cocineros.dto.CocinerosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CocinerosServiceImpl implements ICocinerosService {

    private final CocinerosDAO cocinerosDAO;

    @Override
    public List<CocinerosResponse> findAll() {
        // El DAO ya devuelve directamente la lista de DTOs, no hace falta mapear
        return cocinerosDAO.findAll();
    }

    @Override
    public Optional<CocinerosResponse> findById(Long id) {
        // El DAO devuelve un CocinerosResponse (o null si falla), lo envolvemos en Optional
        CocinerosResponse response = cocinerosDAO.findById(id);
        return Optional.ofNullable(response);
    }

    @Override
    public void save(CocinerosRequest request) {
        // El DAO se encarga de mapear y persistir mediante el request
        cocinerosDAO.save(request);
    }

    @Override
    public boolean update(Long id, CocinerosRequest request) {
        // El DAO ya retorna un boolean indicando si se actualizó con éxito
        return cocinerosDAO.update(id, request);
    }

    @Override
    public boolean delete(Long id) {
        // El DAO ya se encarga de buscar y eliminar, devolviendo un boolean
        return cocinerosDAO.delete(id);
    }
}