package com.example.demo.controller;

import com.example.demo.dto.CustomerModel;
import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Tag(name = "Customer Detail", description = "Endpoints for managing detail of customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @Operation(operationId = "registerCustomer", summary = "Register new customer", description = "Register new customer")
    public CustomerModel registerCustomer(@RequestBody CustomerModel customerModel) {
        return customerService.registerCustomer(customerModel);
    }

    @GetMapping
    @Operation(operationId = "getAllCustomer", summary = "Get all customer", description = "Get all customer")
    public List<CustomerModel> getAllCustomer() {
        return customerService.findAllCustomers();
    }
}
