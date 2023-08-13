package com.example.demo.mapper;

import com.example.demo.dto.CreditCardModel;
import com.example.demo.dto.CustomerModel;
import com.example.demo.dto.PaymentModel;
import com.example.demo.dto.PaymentResponseModel;
import com.example.demo.entity.CreditCard;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Payment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-13T23:01:06+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.3.1 (Oracle Corporation)"
)
@Component
public class MapStructMapperImpl implements MapStructMapper {

    @Override
    public CreditCardModel entityToCreditCardModel(CreditCard creditCard) {
        if ( creditCard == null ) {
            return null;
        }

        CreditCardModel creditCardModel = new CreditCardModel();

        creditCardModel.setCardNumber( creditCard.getCardNumber() );

        return creditCardModel;
    }

    @Override
    public CreditCard dtoToCreditCardModel(CreditCardModel creditCardModel) {
        if ( creditCardModel == null ) {
            return null;
        }

        CreditCard creditCard = new CreditCard();

        creditCard.setCardNumber( creditCardModel.getCardNumber() );

        return creditCard;
    }

    @Override
    public CustomerModel entityToCustomerModel(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerModel customerModel = new CustomerModel();

        customerModel.setCreditCardModel( entityToCreditCardModel( customer.getCreditCard() ) );
        customerModel.setCustomerId( customer.getCustomerId() );
        customerModel.setEmail( customer.getEmail() );

        return customerModel;
    }

    @Override
    public List<CustomerModel> entityToCustomerModelList(List<Customer> customerList) {
        if ( customerList == null ) {
            return null;
        }

        List<CustomerModel> list = new ArrayList<CustomerModel>( customerList.size() );
        for ( Customer customer : customerList ) {
            list.add( entityToCustomerModel( customer ) );
        }

        return list;
    }

    @Override
    public Customer dtoToCustomer(CustomerModel customerModel) {
        if ( customerModel == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setCustomerId( customerModel.getCustomerId() );
        customer.setEmail( customerModel.getEmail() );

        return customer;
    }

    @Override
    public PaymentResponseModel entityToPaymentResponseModel(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentResponseModel paymentResponseModel = new PaymentResponseModel();

        if ( payment.getCreditCard() != null ) {
            if ( paymentResponseModel.getCustomerModel() == null ) {
                paymentResponseModel.setCustomerModel( new CustomerModel() );
            }
            creditCardToCustomerModel( payment.getCreditCard(), paymentResponseModel.getCustomerModel() );
        }
        if ( payment.getCustomer() != null ) {
            if ( paymentResponseModel.getCustomerModel() == null ) {
                paymentResponseModel.setCustomerModel( new CustomerModel() );
            }
            customerToCustomerModel( payment.getCustomer(), paymentResponseModel.getCustomerModel() );
        }
        paymentResponseModel.setPaymentDate( formatDate( payment.getPaymentDate() ) );
        paymentResponseModel.setAmount( payment.getAmount() );

        return paymentResponseModel;
    }

    @Override
    public List<PaymentResponseModel> entityToPaymentResponseModelList(List<Payment> payments) {
        if ( payments == null ) {
            return null;
        }

        List<PaymentResponseModel> list = new ArrayList<PaymentResponseModel>( payments.size() );
        for ( Payment payment : payments ) {
            list.add( entityToPaymentResponseModel( payment ) );
        }

        return list;
    }

    @Override
    public Payment dtoToPayment(PaymentModel paymentModel) {
        if ( paymentModel == null ) {
            return null;
        }

        Payment payment = new Payment();

        payment.setCustomer( dtoToCustomer( paymentModel.getCustomerModel() ) );
        payment.setAmount( paymentModel.getAmount() );

        return payment;
    }

    protected void creditCardToCreditCardModel(CreditCard creditCard, CreditCardModel mappingTarget) {
        if ( creditCard == null ) {
            return;
        }

        mappingTarget.setCardNumber( creditCard.getCardNumber() );
    }

    protected void creditCardToCustomerModel(CreditCard creditCard, CustomerModel mappingTarget) {
        if ( creditCard == null ) {
            return;
        }

        if ( mappingTarget.getCreditCardModel() == null ) {
            mappingTarget.setCreditCardModel( new CreditCardModel() );
        }
        creditCardToCreditCardModel( creditCard, mappingTarget.getCreditCardModel() );
    }

    protected void customerToCustomerModel(Customer customer, CustomerModel mappingTarget) {
        if ( customer == null ) {
            return;
        }

        mappingTarget.setCustomerId( customer.getCustomerId() );
        mappingTarget.setEmail( customer.getEmail() );
    }
}
