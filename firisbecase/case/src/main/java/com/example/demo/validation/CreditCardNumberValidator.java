package com.example.demo.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class CreditCardNumberValidator implements ConstraintValidator<CreditCardNumber, String> {
    private static final Pattern CREDIT_CARD_PATTERN = Pattern.compile("^\\d{13,19}$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return CREDIT_CARD_PATTERN.matcher(value).matches();
    }
}
