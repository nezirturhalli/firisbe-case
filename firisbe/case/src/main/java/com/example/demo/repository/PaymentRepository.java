package com.example.demo.repository;

import com.example.demo.entity.CreditCard;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByCustomer(Optional<Customer> customer);

    List<Payment> findAllByCreditCard(CreditCard cardNumber);

    List<Payment> findAllByPaymentDateBetween(Date paymentDate, Date paymentDate2);

}
