package com.jao.cocineros.model.mapper;

import com.jao.cocineros.model.dto.CocinerosRequest;
import com.jao.cocineros.model.entity.Cocineros;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CocineroRequestMapper {
    @Mappings({
        @Mapping(target = "fechaNacimiento", dateFormat = "dd/MM/yyyy"),
        @Mapping(source = "datosEscuela", target = "datosEscuela")
    })
            Cocineros toCocinero(CocinerosRequest request);

}
