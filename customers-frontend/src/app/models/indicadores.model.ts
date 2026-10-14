export interface Birth {

  mes: number;
  anio: number;
  cantidad: number;
  tasaNatalidad: number;

}
 
export interface BirthPeriodo {
  mes: number;
  anio: number;
  cantidad: number;

}
 
export interface Indicadores {
  nacimientosPorMesAnio: Birth[];
  mayorCantidadNacimientos: BirthPeriodo | null;
  menorCantidadNacimientos: BirthPeriodo | null;

}
 