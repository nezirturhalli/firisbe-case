package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentModel {
    @NotBlank
    private String cardNumber;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private CustomerModel customerModel;
}
