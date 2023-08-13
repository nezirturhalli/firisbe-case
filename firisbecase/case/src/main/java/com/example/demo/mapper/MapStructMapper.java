package com.example.demo.mapper;

import com.example.demo.dto.CustomerModel;
import com.example.demo.dto.PaymentModel;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    //MapStruct Base Configuration
    MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);

    // Customer Entity Conf
    //Entity --> Dto
    CustomerModel entityToCustomerModel(Customer customer);

    // Dto --> Entity
    Customer dtoToCustomer(CustomerModel customerModel);

    // Payment Entity Conf
    // Entity --> Dto
    PaymentModel entityToPaymentModel(Payment payment);

    List<PaymentModel> entityToPaymentModelList(List<Payment> payments);

    // Dto --> Entity
    Payment dtoToPayment(PaymentModel paymentModel);

    List<Payment> dtoToPaymentList(List<PaymentModel> paymentModels);


}
