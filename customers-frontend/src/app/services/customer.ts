import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer as CustomerModel, CustomerRequest } from '../models/customer.model';
import { Indicadores } from '../models/indicadores.model';
 
@Injectable({
  providedIn: 'root'

})

export class CustomerService {
 
  private readonly apiUrl = 'http://localhost:8080/api/customers';
  constructor(private http: HttpClient) {}
 
  getCustomers(dni?: string, email?: string): Observable<CustomerModel[]> {

    let params = new HttpParams();
    if (dni) {
      params = params.set('dni', dni);
    }
 
    if (email) {
      params = params.set('email', email);
    }

    return this.http.get<CustomerModel[]>(this.apiUrl, { params });
  }
 
  createCustomer(customer: CustomerRequest): Observable<CustomerModel> {
    return this.http.post<CustomerModel>(this.apiUrl, customer);
  }
 
  getIndicators(): Observable<Indicadores> {
    return this.http.get<Indicadores>(`${this.apiUrl}/indicadores`);
  }

}
 