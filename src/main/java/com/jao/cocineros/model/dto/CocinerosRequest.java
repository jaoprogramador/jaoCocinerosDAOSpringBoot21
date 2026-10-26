package com.jao.cocineros.model.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jao.cocineros.model.validation.Experiencia;
import com.jao.cocineros.model.validation.MayorDeEdad;
import jakarta.validation.Valid;
import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
@Experiencia
@Getter
@Setter @NoArgsConstructor
@AllArgsConstructor
public class CocinerosRequest {
    private Long id;
    @NotNull(message = "Los nombres no pueden ser nulos")
    @Size(min = 3, max = 40, message = "Los nombres deben tener un mínimo de 3 caracteres y un máximo de 40")
    private String nombre;
    @NotNull(message = "Los apellidos no pueden ser nulos")
    @Size(min = 3, max = 40, message = "Debe tener una especialidad")
    private String especialidad;
    @Min(value = 0, message = "La experiencia mínima es de 0 años")
    @Max(value = 70, message = "La experiencia máxima es de 70 años")
    private Integer anosExperiencia;
    //nuevos campos fechaNacimiento email telefono
    @Pattern(regexp = "^\\+\\d{1,4}\\s\\d{9}$", message = "El teléfono debe incluir el código de país separado del número por un espacio en blanco")
    private String telefono;
    //@Past(message = "La fecha de nacimiento debe ser una fecha en el pasado")
    @Pattern(regexp = "^(0[1-9]|[12]\\d|3[01])/(0[1-9]|1[0-2])/\\d{4}$", message = "Formato de fecha incorrecto. Debe ser dd/mm/aaaa")
    // @MayorDeEdad
    @MayorDeEdad
    private String fechaNacimiento;

    @Email(message = "Formato de correo electrónico inválido")
    private String email;
    private Boolean activo;

    @Valid
    private DatosEscuelaRequest datosEscuela;
}