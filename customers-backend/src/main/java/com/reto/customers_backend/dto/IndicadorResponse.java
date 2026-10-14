package com.reto.customers_backend.dto; 
import java.util.List;
 
public record IndicadorResponse(
        List<BirthResponse> nacimientosPorMesAnio,
        BirthPeriodoResponse mayorCantidadNacimientos,
        BirthPeriodoResponse menorCantidadNacimientos

) {

}
 