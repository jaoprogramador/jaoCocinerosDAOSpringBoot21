package com.jao.cocineros.model.mapper;

import com.jao.cocineros.model.dto.CocinerosRequest;
import com.jao.cocineros.model.entity.Cocineros;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CocineroRequestMapper {
    @Mapping(target = "fechaNacimiento", dateFormat = "dd/MM/yyyy")
    Cocineros toCocinero(CocinerosRequest request);

}
