package com.example.demo.exception;

public class PaymentException extends RuntimeException {

    public PaymentException(String message) {
        super(message);
    }

    public PaymentException(String message, Exception ex) {
        super(message, ex);
    }
}
