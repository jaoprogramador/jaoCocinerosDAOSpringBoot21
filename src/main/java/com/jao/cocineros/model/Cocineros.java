package com.jao.cocineros.model;

import com.jao.cocineros.dto.CocinerosRequest;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cocineros {

    private Long id;

    private String nombre;

    private String especialidad;

    private Integer anosExperiencia;

    private Boolean activo;

    public Cocineros(CocinerosRequest request) {
        this.nombre = request.getNombre();
        this.especialidad = request.getEspecialidad();
        this.anosExperiencia = request.getAnosExperiencia();
        this.activo = request.getActivo();
    }
}