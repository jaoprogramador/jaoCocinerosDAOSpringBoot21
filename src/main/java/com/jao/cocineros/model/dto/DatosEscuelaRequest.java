package com.jao.cocineros.model.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DatosEscuelaRequest {
    @NotBlank(message = "El nombre de la escuela no puede estar vacío u nulo")
    @Size(min = 3, max = 100, message = "El nombre de la escuela debe tener entre 3 y 100 caracteres")
    String nombre;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(max = 150, message = "La dirección no puede superar los 150 caracteres")
    String direccion;

    @NotBlank(message = "La ciudad no puede estar vacía")
    @Size(max = 50, message = "La ciudad no puede superar los 50 caracteres")
    String ciudad;
}

