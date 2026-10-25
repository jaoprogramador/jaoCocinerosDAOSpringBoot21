package com.jao.cocineros.dto;

import com.jao.cocineros.model.Cocineros;
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

    public CocinerosResponse(Long id, String nombre, String especialidad, Integer anosExperiencia, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.anosExperiencia = anosExperiencia;
        this.activo = activo;
    }

    public CocinerosResponse(Cocineros cocinero) {
        this.id = cocinero.getId();
        this.nombre = cocinero.getNombre();
        this.especialidad = cocinero.getEspecialidad();
        this.anosExperiencia = cocinero.getAnosExperiencia();
        this.activo = cocinero.getActivo();
    }
}
