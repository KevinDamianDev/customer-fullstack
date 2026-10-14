import { Component, output, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { CustomerService } from '../../services/customer';
import { Customer } from '../../models/customer.model';

@Component({

  selector: 'app-customer-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './customer-form.html',
  styleUrl: './customer-form.css'

})

export class CustomerForm {

  clienteCreado = output<Customer>();
  guardando = signal(false);
  mensaje = signal('');
  error = signal('');
  customerForm;

  constructor(
    private fb: FormBuilder,
    private customerService: CustomerService

  ) {

    this.customerForm = this.fb.nonNullable.group({

      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      dni: ['', Validators.required],
      fechaN: ['', Validators.required]

    });

  }

  guardar(): void {

    if (this.customerForm.invalid) {
      this.customerForm.markAllAsTouched();
      return;

    }

    this.guardando.set(true);
    this.mensaje.set('');
    this.error.set('');
    this.customerService.createCustomer(
      this.customerForm.getRawValue()

    ).subscribe({
      next: (customer) => {
        this.mensaje.set('Cliente creado correctamente');
        this.guardando.set(false);
        this.customerForm.reset();
        this.clienteCreado.emit(customer);
      },

      error: (error) => {
        console.error('Error creando cliente:', error);
        if (error.status === 409) {
          this.error.set(
            error.error?.mensaje ?? 'El DNI o email ya se encuentra registrado'
          );
        } else {
          this.error.set('No se pudo crear el cliente');
        }
        this.guardando.set(false);
      }

    });

  }

}
