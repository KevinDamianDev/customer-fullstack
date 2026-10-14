package com.reto.customers_backend.exception;
 
public class DuplicateCustomerException extends RuntimeException {
 
    public DuplicateCustomerException(String message) {
        super(message);
    }

}
 