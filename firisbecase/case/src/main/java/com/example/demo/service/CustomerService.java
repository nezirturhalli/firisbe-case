package com.example.demo.service;

import com.example.demo.constant.ErrorMessageConstants;
import com.example.demo.dto.CustomerModel;
import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.mapper.MapStructMapper;
import com.example.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer registerCustomer(CustomerModel customerModel) {
        Optional<Customer> existingCustomer = customerRepository.findCustomerById(customerModel.getCustomerId());
        if (existingCustomer.isPresent()) {
            throw new CustomerNotFoundException(ErrorMessageConstants.EMAIL_ALREADY_EXIST);
        }
        log.info("Customer successfully registered.");
        Customer customer = MapStructMapper.INSTANCE.dtoToCustomer(customerModel);
        return customerRepository.save(customer);
    }

}
