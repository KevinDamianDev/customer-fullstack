package com.reto.customers_backend.service;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.reto.customers_backend.dto.CustomerRequest;
import com.reto.customers_backend.dto.CustomerResponse;
import com.reto.customers_backend.entity.Customer;
import com.reto.customers_backend.exception.DuplicateCustomerException;
import com.reto.customers_backend.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
 
    @Mock
    private CustomerRepository customerRepository;
 
    private CustomerServiceImpl customerService;
 
    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl(customerRepository);
    }
 
    @Test
    void crearClienteCorrectamente() {
 
        CustomerRequest request = new CustomerRequest(
                "Kevin",
                "Gonzales",
                "kevin@gmail.com",
                "12345678",
                LocalDate.of(1995, 10, 10)

        );
 
        when(customerRepository.existsByDni("12345678"))
                .thenReturn(false);
        when(customerRepository.existsByEmail("kevin@gmail.com"))
                .thenReturn(false);
        when(customerRepository.save(any(Customer.class)))
                .thenAnswer(invocation -> {
                    Customer customer = invocation.getArgument(0);
                    customer.setId(1L);
                    return customer;
                });
 
        CustomerResponse response =  customerService.createCustomer(request);
 
        assertEquals(1L, response.id());
        assertEquals("Kevin", response.nombre());
        assertEquals("Gonzales", response.apellido());
        assertEquals("12345678", response.dni());
        assertEquals("kevin@gmail.com", response.email());

    }
 
    @Test
    void debeLanzarErrorCuandoDniYaExiste() {
 
        CustomerRequest request = new CustomerRequest(
                "Kevin",
                "Gonzales",
                "kevin@gmail.com",
                "12345678",
                LocalDate.of(1995, 10, 10)

        );
 
        when(customerRepository.existsByDni("12345678"))
                .thenReturn(true);
        assertThrows(
                DuplicateCustomerException.class,
                () -> customerService.createCustomer(request)

        );

    }

}
 