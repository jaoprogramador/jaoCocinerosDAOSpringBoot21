package com.jao.cocineros.model;

import com.jao.cocineros.dto.CocinerosRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cocineros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cocineros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String especialidad;

    @Column(nullable = false)
    private Integer anosExperiencia;

    @Column(nullable = false)
    private Boolean activo;

    public Cocineros(CocinerosRequest request) {
        this.nombre = request.getNombre();
        this.especialidad = request.getEspecialidad();
        this.anosExperiencia = request.getAnosExperiencia();
        this.activo = request.getActivo();
    }
}