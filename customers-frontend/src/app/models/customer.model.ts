export interface Customer {

  id: number;
  nombre: string;
  apellido: string;
  email: string;
  dni: string;
  createdAt: string;
  fechaN: string;

}
 
export interface CustomerRequest {
  nombre: string;
  apellido: string;
  email: string;
  dni: string;
  fechaN: string;
}
 