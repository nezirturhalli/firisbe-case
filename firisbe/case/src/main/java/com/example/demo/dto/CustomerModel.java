package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerModel {
    @NotNull
    private Long customerId;
    @NotBlank
    private String email;
    @NotNull
    private CreditCardModel creditCardModel;

}
