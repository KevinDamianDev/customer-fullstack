package com.reto.customers_backend.service;


import org.springframework.stereotype.Service;

import com.reto.customers_backend.dto.BirthPeriodoResponse;
import com.reto.customers_backend.dto.BirthResponse;
import com.reto.customers_backend.dto.CustomerRequest;
import com.reto.customers_backend.dto.CustomerResponse;
import com.reto.customers_backend.dto.IndicadorResponse;
import com.reto.customers_backend.entity.Customer;
import com.reto.customers_backend.exception.DuplicateCustomerException;
import com.reto.customers_backend.repository.CustomerRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Comparator;

@Service
public class CustomerServiceImpl implements CustomerService {
 
    private final CustomerRepository customerRepository;
 
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
 
    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {
 
        if (customerRepository.existsByDni(request.dni())) {
            throw new DuplicateCustomerException("Ya existe un cliente registrado con el DNI ingresado");
        }
 
        if (customerRepository.existsByEmail(request.email())) {
            throw new DuplicateCustomerException("Ya existe un cliente registrado con el email ingresado");
        }
 
        Customer customer = new Customer();
        customer.setNombre(request.nombre());
        customer.setApellido(request.apellido());
        customer.setEmail(request.email());
        customer.setDni(request.dni());
        customer.setFechaN(request.fechaN());
        customer.setCreatedAt(LocalDateTime.now());
 
        Customer savedCustomer = customerRepository.save(customer);
        return toResponse(savedCustomer);
    }
 
    @Override
    public List<CustomerResponse> getCustomers(String dni, String email) {
 
        if (dni != null && !dni.isBlank()) {
            return customerRepository.findByDni(dni)
                    .map(this::toResponse)
                    .map(List::of)
                    .orElseGet(List::of);
        }
 
        if (email != null && !email.isBlank()) {
            return customerRepository.findByEmail(email)
                    .map(this::toResponse)
                    .map(List::of)
                    .orElseGet(List::of);
        }
 
        return customerRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }
 
    private CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getNombre(),
                customer.getApellido(),
                customer.getEmail(),
                customer.getDni(),
                customer.getCreatedAt(),
                customer.getFechaN()
        );
    }

    public record MesAnio(int mes, int anio) {
    }


    @Override
    public IndicadorResponse getIndicadores() {
 
    List<Customer> customers = customerRepository.findAll();
 
    if (customers.isEmpty()) {
        return new IndicadorResponse(
                List.of(),
                null,
                null

        );

    }
 
    Map<MesAnio, Long> nacimientosAgrupados = customers.stream()
            .collect(Collectors.groupingBy(
                    customer -> new MesAnio(
                            customer.getFechaN().getMonthValue(),
                            customer.getFechaN().getYear()
                    ),
                    Collectors.counting()

            ));
 
    long totalClientes = customers.size();
    List<BirthResponse> estadisticas = nacimientosAgrupados.entrySet()
            .stream()
            .map(entry -> {
                MesAnio periodo = entry.getKey();
                long cantidad = entry.getValue();
                double tasaNatalidad = ((double) cantidad / totalClientes) * 100;
                tasaNatalidad = Math.round(tasaNatalidad * 100.0) / 100.0;
 
                return new BirthResponse(
                        periodo.mes(),
                        periodo.anio(),
                        cantidad,
                        tasaNatalidad
                );
            })
            .sorted(Comparator.comparingInt(BirthResponse::anio)
                            .thenComparingInt(BirthResponse::mes)
            )
            .toList();
 
    BirthResponse mayor = estadisticas.stream()
            .max(Comparator.comparingLong(
                    BirthResponse::cantidad

            ))
            .orElseThrow();
 
    BirthResponse menor = estadisticas.stream()
            .min(Comparator.comparingLong(
                    BirthResponse::cantidad
            ))
            .orElseThrow();
 
    BirthPeriodoResponse mayorCantidad =
            new BirthPeriodoResponse(
                    mayor.mes(),
                    mayor.anio(),
                    mayor.cantidad()
            );
 
    BirthPeriodoResponse menorCantidad =
            new BirthPeriodoResponse(
                    menor.mes(),
                    menor.anio(),
                    menor.cantidad()
            );
 
    return new IndicadorResponse(
            estadisticas,
            mayorCantidad,
            menorCantidad
    );

    }
 
}