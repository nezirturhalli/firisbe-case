package com.example.demo.service;

import com.example.demo.dto.CreditCardModel;
import com.example.demo.dto.CustomerModel;
import com.example.demo.dto.PaymentModel;
import com.example.demo.dto.PaymentResponseModel;
import com.example.demo.entity.CreditCard;
import com.example.demo.entity.Payment;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.exception.PaymentException;
import com.example.demo.repository.CreditCardRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CreditCardRepository creditCardRepository;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMakePaymentSuccess() {
        PaymentModel paymentModel = new PaymentModel();
        paymentModel.setAmount(BigDecimal.valueOf(100));


        CreditCardModel creditCardModel = new CreditCardModel();
        creditCardModel.setCardNumber("1669098601286363");

        CustomerModel customerModel = new CustomerModel();
        customerModel.setCustomerId(1L);
        customerModel.setEmail("abc@gmail.com");
        customerModel.setCreditCardModel(creditCardModel);

        paymentModel.setCustomerModel(customerModel);


        PaymentResponseModel response = paymentService.makePayment(paymentModel);

        assertNotNull(response);
    }

    @Test
    void testMakePaymentInvalidAmount() {
        PaymentModel paymentModel = new PaymentModel();
        paymentModel.setAmount(BigDecimal.ZERO);
        CreditCardModel creditCardModel = new CreditCardModel();
        creditCardModel.setCardNumber("1669098601286363");

        CustomerModel customerModel = new CustomerModel();
        customerModel.setCustomerId(1L);
        customerModel.setEmail("abc@gmail.com");
        customerModel.setCreditCardModel(creditCardModel);

        paymentModel.setCustomerModel(customerModel);

        assertThrows(PaymentException.class, () -> paymentService.makePayment(paymentModel));
    }

    @Test
    void testGetPaymentsByCustomerNotFound() {
        Long customerId = 1L;
        when(customerRepository.findCustomerByCustomerId(customerId)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> paymentService.getPaymentsByCustomer(customerId));
    }

    @Test
    void testGetPaymentsByCardNumberSuccess() {
        String cardNumber = "1234567890123456";
        CreditCard existingCreditCard = new CreditCard();
        when(creditCardRepository.findCreditCardByCardNumber(cardNumber)).thenReturn(existingCreditCard);

        List<PaymentResponseModel> responseList = paymentService.getPaymentsByCardNumber(cardNumber);

        assertNotNull(responseList);
    }

    @Test
    void testGetPaymentsByDateRangeSuccess() throws ParseException {
        String startDate = "01/01/2023";
        String endDate = "12/31/2023";

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date queryStartDate = dateFormat.parse(startDate);
        Date queryEndDate = dateFormat.parse(endDate);

        List<Payment> paymentList = new ArrayList<>(); // Initialize payment list with sample data
        when(paymentRepository.findAllByPaymentDateBetween(queryStartDate, queryEndDate)).thenReturn(paymentList);

        List<PaymentResponseModel> responseList = paymentService.getPaymentsByDateRange(startDate, endDate);

        assertNotNull(responseList);
    }
}
