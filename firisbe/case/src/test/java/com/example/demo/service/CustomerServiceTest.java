package com.example.demo.service;

import com.example.demo.dto.CreditCardModel;
import com.example.demo.dto.CustomerModel;
import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.repository.CreditCardRepository;
import com.example.demo.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CreditCardRepository creditCardRepository;

    @InjectMocks
    private CustomerService customerService;

    // Initialize mock objects
    public CustomerServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterCustomerSuccess() {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setCustomerId(1L);
        customerModel.setEmail("abc@gmail.com");
        CreditCardModel creditCardModel = new CreditCardModel();
        creditCardModel.setCardNumber("1669098601286363");
        customerModel.setCreditCardModel(creditCardModel);
        // Mock behavior for repository methods
        when(customerRepository.findCustomerByCustomerId(anyLong())).thenReturn(Optional.empty());
        when(creditCardRepository.findCreditCardByCardNumber(anyString())).thenReturn(null);

        // Call the service method
        CustomerModel response = customerService.registerCustomer(customerModel);

        // Assert
        assertNotNull(response);
        // Add more assertions based on the expected behavior of the service method
    }

    @Test
    void testRegisterCustomerCustomerExists() {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setCustomerId(1L);
        customerModel.setEmail("abc@gmail.com");
        CreditCardModel creditCardModel = new CreditCardModel();
        creditCardModel.setCardNumber("1669098601286363");
        customerModel.setCreditCardModel(creditCardModel);

        // Mock behavior for repository methods
        when(customerRepository.findCustomerByCustomerId(anyLong())).thenReturn(Optional.of(new Customer()));

        // Call the service method and expect an exception
        assertThrows(CustomerNotFoundException.class, () -> customerService.registerCustomer(customerModel));
    }

    // Add similar tests for other scenarios if necessary

    // Example test for findAllCustomers
    @Test
    void testFindAllCustomers() {
        // Prepare mock data
        List<Customer> customerList = new ArrayList<>(); // Initialize with mock customer entities
        when(customerRepository.findAll()).thenReturn(customerList);

        // Call the service method
        List<CustomerModel> responseList = customerService.findAllCustomers();

        assertNotNull(responseList);
        // Add more assertions based on the expected behavior of the service method
    }
}
