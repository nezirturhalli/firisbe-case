package com.example.demo.repository;

import com.example.demo.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    CreditCard findCreditCardByCardNumber(String cardNumber);
}
