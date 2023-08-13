package com.example.demo.mapper;

import com.example.demo.dto.CreditCardModel;
import com.example.demo.dto.CustomerModel;
import com.example.demo.dto.PaymentModel;
import com.example.demo.dto.PaymentResponseModel;
import com.example.demo.entity.CreditCard;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    //MapStruct Base Configuration
    MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);


    // Card Entity Conf
    //Entity --> Dto

    CreditCardModel entityToCreditCardModel(CreditCard creditCard);
    CreditCard dtoToCreditCardModel(CreditCardModel creditCardModel);

    // Customer Entity Conf
    //Entity --> Dto
    @Mapping(target = "creditCardModel", source = "creditCard")
    CustomerModel entityToCustomerModel(Customer customer);

    @Mapping(target = "creditCardModel", source = "creditCard")
    List<CustomerModel> entityToCustomerModelList(List<Customer> customerList);

    // Dto --> Entity
    Customer dtoToCustomer(CustomerModel customerModel);


    // Payment Entity Conf
    // Entity --> Dto

    @Mapping(target = "customerModel", source = "customer")
    @Mapping(target = "paymentDate", source = "paymentDate", qualifiedByName = "formatDate")
    @Mapping(target = "customerModel.creditCardModel.cardNumber", source = "creditCard.cardNumber")
    PaymentResponseModel entityToPaymentResponseModel(Payment payment);

    @Mapping(target = "customerModel", source = "customer")
    @Mapping(target = "paymentDate", source = "paymentDate", qualifiedByName = "formatDate")
    List<PaymentResponseModel> entityToPaymentResponseModelList(List<Payment> payments);


    // Dto --> Entity
    @Mapping(target = "customer", source = "customerModel")
    @Mapping(target = "paymentDate", ignore = true)
    Payment dtoToPayment(PaymentModel paymentModel);

    @Named("formatDate")
    default String formatDate(Date date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return zonedDateTime.format(formatter);
    }

}
