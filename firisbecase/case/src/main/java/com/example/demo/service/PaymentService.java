package com.example.demo.service;

import com.example.demo.constant.ErrorMessageConstants;
import com.example.demo.dto.PaymentModel;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Payment;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.exception.PaymentException;
import com.example.demo.mapper.MapStructMapper;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;

    public Payment makePayment(PaymentModel paymentModel) {
        if (paymentModel.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new PaymentException(ErrorMessageConstants.AMOUNT_MUST_BE_GREATER_ZERO);
        }
        log.info("Payment successful.");
        Payment payment = MapStructMapper.INSTANCE.dtoToPayment(paymentModel);
        return paymentRepository.save(payment);
    }

    public List<PaymentModel> getPaymentsByCustomer(Long customerId) {
        Optional<Customer> existingCustomer = customerRepository.findCustomerById(customerId);
        if (existingCustomer.isEmpty()) {
            throw new CustomerNotFoundException(ErrorMessageConstants.CUSTOMER_NOT_FOUND);
        }
        var paymentList = paymentRepository.findAllByCustomer(existingCustomer);
        return MapStructMapper.INSTANCE.entityToPaymentModelList(paymentList);
    }

    public List<Payment> getPaymentsByCardNumber(Long customerId, String cardNumber) {
        Optional<Customer> existingCustomer = customerRepository.findCustomerById(customerId);
        if (existingCustomer.isEmpty()) {
            throw new CustomerNotFoundException(ErrorMessageConstants.CUSTOMER_NOT_FOUND);
        }
        return paymentRepository.findAllByCustomerAndCardNumber(existingCustomer, cardNumber);
    }

    public List<Payment> getPaymentsByDateRange(Long customerId, Date startDate, Date endDate) {
        Optional<Customer> existingCustomer = customerRepository.findCustomerById(customerId);
        if (existingCustomer.isEmpty()) {
            throw new CustomerNotFoundException(ErrorMessageConstants.CUSTOMER_NOT_FOUND);
        }
        return paymentRepository.findAllByCustomerAndPaymentDateBetween(existingCustomer, startDate, endDate);
    }

}
