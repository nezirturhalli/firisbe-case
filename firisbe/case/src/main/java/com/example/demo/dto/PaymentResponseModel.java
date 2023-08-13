package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentResponseModel {


    private BigDecimal amount;

    private String paymentDate;

    private CustomerModel customerModel;

}
