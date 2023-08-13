package com.example.demo.controller;

import com.example.demo.dto.CustomerModel;
import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Tag(name = "Customer Detail", description = "Endpoints for managing detail of customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @Operation(operationId = "registerCustomer", summary = "Register new customer", description = "Register new customer ")
    public Customer registerCustomer(@RequestBody CustomerModel customerModel) {
        return customerService.registerCustomer(customerModel);
    }
}
