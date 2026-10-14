import { Component, signal } from '@angular/core';

import { CustomerForm } from './components/customer-form/customer-form';
import { CustomerList } from './components/customer-list/customer-list';
import { CustomerIndicators } from './components/customer-indicators/customer-indicators';

@Component({
  selector: 'app-root',
  imports: [
    CustomerForm,
    CustomerList,
    CustomerIndicators
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {

  refreshCustomers = signal(0);

  onCustomerCreated(): void {
    this.refreshCustomers.update(value => value + 1);
  }
}