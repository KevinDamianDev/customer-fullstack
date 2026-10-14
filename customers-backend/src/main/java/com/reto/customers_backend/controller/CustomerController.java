package com.reto.customers_backend.controller;

 
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.reto.customers_backend.dto.CustomerRequest;
import com.reto.customers_backend.dto.CustomerResponse;
import com.reto.customers_backend.dto.IndicadorResponse;
import com.reto.customers_backend.service.CustomerService;

import java.util.List;
 
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
 
    private final CustomerService  customerService;
 
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
 
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest request) {
        CustomerResponse customer = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
 
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getCustomers( @RequestParam(required = false) String dni,
            @RequestParam(required = false) String email) {
        return ResponseEntity.ok(customerService.getCustomers(dni, email));
    }

    @GetMapping("/indicadores")
    public ResponseEntity<IndicadorResponse> getIndicators() {
        return ResponseEntity.ok(customerService.getIndicadores());

    }
 
}
