package com.example.demo.controller;

import com.example.demo.dto.PaymentModel;
import com.example.demo.dto.PaymentResponseModel;
import com.example.demo.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@Tag(name = "Payment Detail", description = "Endpoints for managing detail of payment")
public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping
    @Operation(operationId = "makePayment", summary = "Placing a new payment", description = "Placing a new payment")
    public PaymentResponseModel makePayment(@RequestBody PaymentModel paymentModel) {
        return paymentService.makePayment(paymentModel);
    }

    @GetMapping("/by-customer/{customerId}")
    @Operation(operationId = "getPaymentsByCustomer", summary = " List all payments by customerId", description = " List all payments my customerId")
    public List<PaymentResponseModel> getPaymentsByCustomer(@PathVariable Long customerId) {
        return paymentService.getPaymentsByCustomer(customerId);
    }

    @GetMapping("/by-card-number/{cardNumber}")
    @Operation(operationId = "getPaymentsByCardNumber", summary = " List all payments by customer card", description = " List all payments by customer card")
    public List<PaymentResponseModel> getPaymentsByCardNumber(
            @PathVariable String cardNumber) {
        return paymentService.getPaymentsByCardNumber(cardNumber);
    }

    @GetMapping("/by-date-range")
    @Operation(operationId = "getPaymentsByDateRange", summary = " List all payments by date range", description = " List all payments by date range")
    public List<PaymentResponseModel> getPaymentsByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) throws ParseException {
        return paymentService.getPaymentsByDateRange(startDate, endDate);
    }

}

