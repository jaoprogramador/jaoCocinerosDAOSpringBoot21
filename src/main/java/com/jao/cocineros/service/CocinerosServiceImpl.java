package com.jao.cocineros.service;

import com.jao.cocineros.dao.CocinerosDAO;
import com.jao.cocineros.model.dto.CocinerosRequest;
import com.jao.cocineros.model.dto.CocinerosResponse;
import com.jao.cocineros.model.entity.Cocineros;
import com.jao.cocineros.model.mapper.CocineroRequestMapper;
import com.jao.cocineros.model.mapper.CocineroResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CocinerosServiceImpl implements ICocinerosService {

    private final CocinerosDAO cocinerosDAO;
    private final CocineroRequestMapper cocineroRequestMapper;
    private final CocineroResponseMapper cocineroResponseMapper;

    @Override
    public List<CocinerosResponse> findAll() {
        // 1. El DAO devuelve una lista de Entidades
        List<Cocineros> cocineros = cocinerosDAO.findAll();
        // 2. El ResponseMapper convierte la lista de Entidades a DTOs Response
        return cocineroResponseMapper.toCocineroResponseList(cocineros);
    }

    @Override
    public Optional<CocinerosResponse> findById(Long id) {
        // 1. El DAO devuelve una Entidad (o null)
        Cocineros cocinero = cocinerosDAO.findById(id);
        if (cocinero == null) {
            return Optional.empty();
        }
        // 2. Mapeamos la Entidad a Response y la envolvemos en un Optional
        return Optional.of(cocineroResponseMapper.toCocineroResponse(cocinero));
    }

    @Override
    public CocinerosResponse save(CocinerosRequest request) {
        // 1. Convertimos el Request (DTO) a Entidad usando el RequestMapper
        Cocineros cocinero = cocineroRequestMapper.toCocinero(request);

        // 2. Guardamos la entidad en la base de datos a través del DAO
        cocinerosDAO.save(cocinero);

        // 3. Convertimos la entidad guardada a Response para devolverla al controlador
        return cocineroResponseMapper.toCocineroResponse(cocinero);
    }

    @Override
    public boolean update(Long id, CocinerosRequest request) {
        // 1. Convertimos el Request a Entidad
        Cocineros cocinero = cocineroRequestMapper.toCocinero(request);

        // 2. Pasamos la entidad al DAO para realizar la actualización
        return cocinerosDAO.update(id, cocinero);
    }

    @Override
    public boolean delete(Long id) {
        // El DAO se encarga de eliminar directamente por ID
        return cocinerosDAO.delete(id);
    }
}