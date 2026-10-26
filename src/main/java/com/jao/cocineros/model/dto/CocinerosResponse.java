package com.jao.cocineros.model.dto;

import com.jao.cocineros.model.entity.Cocineros;
import lombok.*;

@Getter
@Setter @NoArgsConstructor
@AllArgsConstructor
public class CocinerosResponse {
    private Long id;
    private String nombre;
    private String especialidad;
    private Integer anosExperiencia;
    private Boolean activo;

    private DatosEscuelaRequest datosEscuela;


}
