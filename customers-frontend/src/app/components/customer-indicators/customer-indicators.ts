import { Component, OnInit, input, effect, signal } from '@angular/core';

import { CustomerService } from '../../services/customer';
import { Indicadores } from '../../models/indicadores.model';

@Component({
  selector: 'app-customer-indicators',
  standalone: true,
  imports: [],
  templateUrl: './customer-indicators.html',
  styleUrl: './customer-indicators.css'
})
export class CustomerIndicators implements OnInit {

  refreshTrigger = input(0);

  indicators = signal<Indicadores | null>(null);
  loading = signal(false);
  error = signal('');

  constructor(private customerService: CustomerService) {
    effect(() => {
      const refresh = this.refreshTrigger();

      if (refresh > 0) {
        this.loadIndicators();
      }
    });
  }

  ngOnInit(): void {
    this.loadIndicators();
  }

  loadIndicators(): void {
    this.loading.set(true);
    this.error.set('');

    this.customerService.getIndicators().subscribe({
      next: (indicators) => {
        this.indicators.set(indicators);
        this.loading.set(false);
      },
      error: (error) => {
        console.error('Error cargando indicadores:', error);
        this.error.set('No se pudieron cargar los indicadores');
        this.loading.set(false);
      }
    });
  }
}