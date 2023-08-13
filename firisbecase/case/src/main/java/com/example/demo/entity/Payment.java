package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "PAYMENTS")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Payment {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAYMENT_ID")
    private Long paymentId;
    @Column(name = "CARD_NUMBER",unique = true)
    @CreditCardNumber
    private String cardNumber;
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "PAYMENT_DATE",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // Other fields, getters, setters...
}
