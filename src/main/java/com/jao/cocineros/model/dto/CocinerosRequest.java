package com.jao.cocineros.model.dto;
import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Data
@Builder
public class CocinerosRequest {
    private Long id;
    @NotNull(message = "Los nombres no pueden ser nulos")
    @Size(min = 3, max = 40, message = "Los nombres deben tener un mínimo de 3 caracteres y un máximo de 40")
    private String nombre;
    @NotNull(message = "Los apellidos no pueden ser nulos")
    @Size(min = 3, max = 40, message = "Debe tener una especialidad")
    private String especialidad;
    private Integer anosExperiencia;
    //nuevos campos fechaNacimiento email telefono
    @Pattern(regexp = "^\\+\\d{1,4}\\s\\d{9}$", message = "El teléfono debe incluir el código de país separado del número por un espacio en blanco")
    private String telefono;
    @Past(message = "La fecha de nacimiento debe ser una fecha en el pasado")
    private LocalDate fechaNacimiento;

    @Email(message = "Formato de correo electrónico inválido")
    private String email;
    private Boolean activo;
}