package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "CUSTOMERS")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Customer {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "CUSTOMER_ID")
    private Long customerId;


    @Column(unique = true, name = "EMAIL")
    @Email
    private String email;

    @OneToMany(mappedBy = "customer")
    private List<Payment> payments;

    @OneToOne
    @JoinColumn(name = "CARD_ID")
    private CreditCard creditCard;
    // Other fields, getters, setters...
}
