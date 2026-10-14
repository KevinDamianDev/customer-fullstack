package com.reto.customers_backend.dto;

public record BirthPeriodoResponse(
        int mes,
        int anio,
        long cantidad
) {
}