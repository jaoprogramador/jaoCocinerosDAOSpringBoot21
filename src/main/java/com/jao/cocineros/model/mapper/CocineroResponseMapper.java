package com.jao.cocineros.model.mapper;

import com.jao.cocineros.model.dto.CocinerosResponse;
import com.jao.cocineros.model.entity.Cocineros;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CocineroResponseMapper {
    @Mappings(
            @Mapping(source = "id", target = "id")
    )
    @Mapping(source = "datosEscuela", target = "datosEscuela")
    CocinerosResponse toCocineroResponse(Cocineros cocinero);

    List<CocinerosResponse> toCocineroResponseList(List<Cocineros> cocineros);
}
