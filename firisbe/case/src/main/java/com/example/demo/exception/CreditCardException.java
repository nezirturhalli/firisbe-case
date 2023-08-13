package com.example.demo.exception;

public class CreditCardException extends RuntimeException {

    public CreditCardException(String message) {
        super(message);
    }

    public CreditCardException(String message, Exception ex) {
        super(message, ex);
    }
}
