import { Component, output, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({

  selector: 'app-customer-filter',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './customer-filter.html',
  styleUrl: './customer-filter.css'

})

export class CustomerFilter {

  dni = signal('');
  email = signal('');
  buscar = output<{ dni: string; email: string }>();
  limpiar = output<void>();

  buscarClientes(): void {
    this.buscar.emit({
      dni: this.dni().trim(),
      email: this.email().trim()

    });

  }

  limpiarFiltros(): void {
    this.dni.set('');
    this.email.set('');
    this.limpiar.emit();

  }

}
