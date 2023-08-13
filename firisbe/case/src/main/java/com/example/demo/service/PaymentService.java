package com.example.demo.service;

import com.example.demo.constant.ErrorMessageConstants;
import com.example.demo.dto.PaymentModel;
import com.example.demo.dto.PaymentResponseModel;
import com.example.demo.entity.CreditCard;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Payment;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.exception.PaymentException;
import com.example.demo.mapper.MapStructMapper;
import com.example.demo.repository.CreditCardRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;
    private final CreditCardRepository creditCardRepository;

    public PaymentResponseModel makePayment(PaymentModel paymentModel) {
        CreditCard creditCard = creditCardRepository.findCreditCardByCardNumber(paymentModel.getCustomerModel().getCreditCardModel().getCardNumber());
        if (paymentModel.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new PaymentException(ErrorMessageConstants.AMOUNT_MUST_BE_GREATER_ZERO);
        }
        log.info("Payment successful.");
        Payment payment = MapStructMapper.INSTANCE.dtoToPayment(paymentModel);

        // Create a ZonedDateTime instance in the desired time zone
        ZoneId zoneId = ZoneId.of("Europe/Istanbul");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);

        // Set the formatted date to the paymentEntity
        payment.setPaymentDate(Date.from(zonedDateTime.toInstant()));
        payment.setCreditCard(creditCard);
        paymentRepository.save(payment);


        return MapStructMapper.INSTANCE.entityToPaymentResponseModel(payment);
    }

    public List<PaymentResponseModel> getPaymentsByCustomer(Long customerId) {
        Optional<Customer> existingCustomer = customerRepository.findCustomerByCustomerId(customerId);
        if (existingCustomer.isEmpty()) {
            throw new CustomerNotFoundException(ErrorMessageConstants.CUSTOMER_NOT_FOUND);
        }
        var paymentList = paymentRepository.findAllByCustomer(existingCustomer);
        return MapStructMapper.INSTANCE.entityToPaymentResponseModelList(paymentList);
    }

    public List<PaymentResponseModel> getPaymentsByCardNumber(String cardNumber) {
        CreditCard existingCreditCard = creditCardRepository.findCreditCardByCardNumber(cardNumber);
        var paymentList = paymentRepository.findAllByCreditCard(existingCreditCard);
        return MapStructMapper.INSTANCE.entityToPaymentResponseModelList(paymentList);
    }

    public List<PaymentResponseModel> getPaymentsByDateRange(String startDate, String endDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date queryStartDate = dateFormat.parse(startDate);
        Date queryEndDate = dateFormat.parse(endDate);

        var paymentList = paymentRepository.findAllByPaymentDateBetween(queryStartDate, queryEndDate);

        return MapStructMapper.INSTANCE.entityToPaymentResponseModelList(paymentList);
    }


}
