package com.jao.cocineros.model.entity;

import com.jao.cocineros.model.dto.CocinerosRequest;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cocineros {

    private Long id;
    private String nombre;
    private String especialidad;
    private Integer anosExperiencia;
    private String email;
    private String telefono;
    private String fechaNacimiento;
    private Boolean activo;
    private DatosEscuela datosEscuela;

    public Cocineros(CocinerosRequest request) {
        this.nombre = request.getNombre();
        this.especialidad = request.getEspecialidad();
        this.anosExperiencia = request.getAnosExperiencia();
        this.email = request.getEmail();
        this.telefono = request.getTelefono();
        this.fechaNacimiento = request.getFechaNacimiento();
        this.activo = request.getActivo();
    }
}