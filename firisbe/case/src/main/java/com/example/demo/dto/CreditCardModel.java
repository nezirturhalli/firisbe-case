package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreditCardModel {
    @NotBlank
    private String cardNumber;
}
