package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerModel {
    private Long customerId;
    @NotBlank
    private String email;
}
