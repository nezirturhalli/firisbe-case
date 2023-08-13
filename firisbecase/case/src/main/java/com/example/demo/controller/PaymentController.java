package com.example.demo.controller;

import com.example.demo.dto.PaymentModel;
import com.example.demo.entity.Payment;
import com.example.demo.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@Tag(name = "Payment Detail", description = "Endpoints for managing detail of payment")
public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping
    @Operation(operationId = "makePayment", summary = "Placing a new payment", description = "Placing a new payment")
    public Payment makePayment(@RequestBody PaymentModel paymentModel) {
        return paymentService.makePayment(paymentModel);
    }

    @GetMapping("/by-customer/{customerId}")
    @Operation(operationId = "getPaymentsByCustomer", summary = " List all payments of customer", description = " List all payments of customer")
    public List<PaymentModel> getPaymentsByCustomer(@PathVariable Long customerId) {
        return paymentService.getPaymentsByCustomer(customerId);
    }

    @GetMapping("/by-card-number/{customerId}/{cardNumber}")
    @Operation(operationId = "getPaymentsByCardNumber", summary = " List all payments of customer card", description = " List all payments of customer card")
    public List<Payment> getPaymentsByCardNumber(
            @PathVariable Long customerId,
            @PathVariable String cardNumber) {
        return paymentService.getPaymentsByCardNumber(customerId, cardNumber);
    }

    @GetMapping("/by-date-range/{customerId}")
    @Operation(operationId = "getPaymentsByDateRange", summary = " List all payments of date range", description = " List all payments of date range")
    public List<Payment> getPaymentsByDateRange(
            @PathVariable Long customerId,
            @RequestParam Date startDate,
            @RequestParam Date endDate) {
        return paymentService.getPaymentsByDateRange(customerId, startDate, endDate);
    }

}

