import { Component, OnInit, input, effect, signal} from '@angular/core';
import { CustomerFilter } from '../customer-filter/customer-filter';
import { CustomerService } from '../../services/customer';
import { Customer } from '../../models/customer.model';
 
@Component({

  selector: 'app-customer-list',
  standalone: true,
  imports: [CustomerFilter],
  templateUrl: './customer-list.html',

  styleUrl: './customer-list.css'

})

export class CustomerList implements OnInit {
 
  customers = signal<Customer[]>([]);
  loading = signal(false);
  error = signal('');
  refreshTrigger = input(0);
 
 constructor(private customerService: CustomerService) {

  effect(() => {
    const refresh = this.refreshTrigger();

    if (refresh > 0) {
      this.loadCustomers();
    }
  });
}
 
  ngOnInit(): void {
    this.loadCustomers();
  }
 
 loadCustomers(dni?: string, email?: string): void {

  this.loading.set(true);
  this.error.set('');
  this.customerService.getCustomers(dni, email).subscribe({

    next: (customers) => {
      this.customers.set(customers);
      this.loading.set(false);

    },

    error: (error) => {
      console.error('Error cargando clientes:', error);
      this.error.set('No se pudieron cargar los clientes');
      this.loading.set(false);

    }

  });

}
 
buscarClientes(filtros: { dni: string; email: string }): void {
  this.loadCustomers(
    filtros.dni || undefined,
    filtros.email || undefined

  );

}
 
limpiarFiltros(): void {
  this.loadCustomers();
}
 
}
 