import { TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import {
  HttpTestingController,
  provideHttpClientTesting
} from '@angular/common/http/testing';

import { CustomerService } from './customer';
import { Customer } from '../models/customer.model';

describe('CustomerService', () => {

  let service: CustomerService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        CustomerService,
        provideHttpClient(),
        provideHttpClientTesting()
      ]
    });

    service = TestBed.inject(CustomerService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('debe obtener la lista de clientes', () => {

    const clientesMock: Customer[] = [
      {
        id: 1,
        nombre: 'Kevin',
        apellido: 'Gonzales',
        email: 'kevin@gmail.com',
        dni: '12345678',
        createdAt: '2026-10-14T10:00:00',
        fechaN: '1995-10-10'
      }
    ];

    service.getCustomers().subscribe(clientes => {
      expect(clientes.length).toBe(1);
      expect(clientes[0].nombre).toBe('Kevin');
      expect(clientes[0].dni).toBe('12345678');
    });

    const request = httpMock.expectOne(
      'http://localhost:8080/api/customers'
    );

    expect(request.request.method).toBe('GET');

    request.flush(clientesMock);
  });

});