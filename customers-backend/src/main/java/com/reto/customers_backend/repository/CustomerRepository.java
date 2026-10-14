package com.reto.customers_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reto.customers_backend.entity.Customer;

import java.util.Optional;
 
public interface CustomerRepository extends JpaRepository<Customer, Long> {
 
    Optional<Customer> findByDni(String dni);
 
    Optional<Customer> findByEmail(String email);
 
    boolean existsByDni(String dni);
 
    boolean existsByEmail(String email);
}