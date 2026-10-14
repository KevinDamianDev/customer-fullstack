package com.reto.customers_backend.service;

 
import java.util.List;

import com.reto.customers_backend.dto.CustomerRequest;
import com.reto.customers_backend.dto.CustomerResponse;
import com.reto.customers_backend.dto.IndicadorResponse;
 
public interface CustomerService {
 
    CustomerResponse createCustomer(CustomerRequest request);
    List<CustomerResponse> getCustomers(String dni, String email);
    IndicadorResponse getIndicadores();
}