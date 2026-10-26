package com.jao.cocineros.model.validation;

import com.jao.cocineros.model.dto.CocinerosRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class ExperienciaValidator implements ConstraintValidator<Experiencia, CocinerosRequest> {
    @Override
    public boolean isValid(CocinerosRequest personaRequest, ConstraintValidatorContext constraintValidatorContext) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNacimiento;
        try {
            fechaNacimiento = LocalDate.parse(personaRequest.getFechaNacimiento(), formatter);
        } catch (DateTimeParseException e) {
            return false;
        }

        long edad = ChronoUnit.YEARS.between(fechaNacimiento, LocalDate.now());
        System.out.println("ExperienciaValidator.isValid edad "+edad);
        System.out.println("ExperienciaValidator.isValid anosExp "+personaRequest.getAnosExperiencia());
        return edad >= 18 + personaRequest.getAnosExperiencia().intValue();
    }
}
