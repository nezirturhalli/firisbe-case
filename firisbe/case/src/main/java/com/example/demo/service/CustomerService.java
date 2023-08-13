package com.example.demo.service;

import com.example.demo.constant.ErrorMessageConstants;
import com.example.demo.dto.CustomerModel;
import com.example.demo.entity.CreditCard;
import com.example.demo.entity.Customer;
import com.example.demo.exception.CreditCardException;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.mapper.MapStructMapper;
import com.example.demo.repository.CreditCardRepository;
import com.example.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CreditCardRepository creditCardRepository;

    public CustomerModel registerCustomer(CustomerModel customerModel) {
        Optional<Customer> existingCustomer = customerRepository.findCustomerByCustomerId(customerModel.getCustomerId());
        CreditCard existingCreditCard = creditCardRepository.findCreditCardByCardNumber(customerModel.getCreditCardModel().getCardNumber());
        if (existingCustomer.isPresent()) {
            throw new CustomerNotFoundException(ErrorMessageConstants.EMAIL_ALREADY_EXIST);
        }
        if (existingCreditCard != null) {
            throw new CreditCardException(ErrorMessageConstants.CREDIT_CARD_ALREADY_EXIST);
        }
        log.info("Customer successfully registered.");
        Customer customer = MapStructMapper.INSTANCE.dtoToCustomer(customerModel);
        CreditCard creditCard = MapStructMapper.INSTANCE.dtoToCreditCardModel(customerModel.getCreditCardModel());
        customer.setCreditCard(creditCard);
        creditCardRepository.save(creditCard);
        customerRepository.save(customer);
        return customerModel;
    }

    public List<CustomerModel> findAllCustomers() {
        var customerList = customerRepository.findAll();
        return MapStructMapper.INSTANCE.entityToCustomerModelList(customerList);


    }

}
