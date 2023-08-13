package com.example.demo.mapper;

import com.example.demo.dto.CustomerModel;
import com.example.demo.dto.PaymentModel;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Payment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-13T19:29:38+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.3.1 (Oracle Corporation)"
)
@Component
public class MapStructMapperImpl implements MapStructMapper {

    @Override
    public CustomerModel entityToCustomerModel(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerModel customerModel = new CustomerModel();

        customerModel.setEmail( customer.getEmail() );

        return customerModel;
    }

    @Override
    public Customer dtoToCustomer(CustomerModel customerModel) {
        if ( customerModel == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setEmail( customerModel.getEmail() );

        return customer;
    }

    @Override
    public PaymentModel entityToPaymentModel(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentModel paymentModel = new PaymentModel();

        paymentModel.setCardNumber( payment.getCardNumber() );
        paymentModel.setAmount( payment.getAmount() );

        return paymentModel;
    }

    @Override
    public List<PaymentModel> entityToPaymentModelList(List<Payment> payments) {
        if ( payments == null ) {
            return null;
        }

        List<PaymentModel> list = new ArrayList<PaymentModel>( payments.size() );
        for ( Payment payment : payments ) {
            list.add( entityToPaymentModel( payment ) );
        }

        return list;
    }

    @Override
    public Payment dtoToPayment(PaymentModel paymentModel) {
        if ( paymentModel == null ) {
            return null;
        }

        Payment payment = new Payment();

        payment.setCardNumber( paymentModel.getCardNumber() );
        payment.setAmount( paymentModel.getAmount() );

        return payment;
    }

    @Override
    public List<Payment> dtoToPaymentList(List<PaymentModel> paymentModels) {
        if ( paymentModels == null ) {
            return null;
        }

        List<Payment> list = new ArrayList<Payment>( paymentModels.size() );
        for ( PaymentModel paymentModel : paymentModels ) {
            list.add( dtoToPayment( paymentModel ) );
        }

        return list;
    }
}
