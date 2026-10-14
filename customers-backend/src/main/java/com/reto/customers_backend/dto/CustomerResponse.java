package com.reto.customers_backend.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
 
public record CustomerResponse(
        Long id,
        String nombre,
        String apellido,
        String email,
        String dni,
        LocalDateTime createdAt,
        LocalDate fechaN
) {
}