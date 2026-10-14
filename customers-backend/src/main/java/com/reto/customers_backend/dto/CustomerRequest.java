package com.reto.customers_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
 
import java.time.LocalDate;
 
public record CustomerRequest(
 
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,
 
        @NotBlank(message = "El apellido es obligatorio")
        String apellido,
 
        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El formato del email no es válido")
        String email,
 
        @NotBlank(message = "El DNI es obligatorio")
        String dni,
 
        @NotNull(message = "La fecha de nacimiento es obligatoria")
        @Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
        LocalDate fechaN
 
) {
}