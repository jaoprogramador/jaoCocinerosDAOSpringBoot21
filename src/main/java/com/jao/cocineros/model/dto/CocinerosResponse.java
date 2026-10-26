package com.jao.cocineros.model.dto;

import com.jao.cocineros.model.entity.Cocineros;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CocinerosResponse {
    private Long id;
    private String nombre;
    private String especialidad;
    private Integer anosExperiencia;
    private Boolean activo;


}
