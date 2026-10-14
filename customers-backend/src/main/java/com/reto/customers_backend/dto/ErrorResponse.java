package com.reto.customers_backend.dto;

import java.time.LocalDateTime;
 
public record ErrorResponse(
        int status,
        String mensaje,
        LocalDateTime timestamp

) {
}
 