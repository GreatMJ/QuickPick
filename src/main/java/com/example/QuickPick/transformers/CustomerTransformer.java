package com.example.QuickPick.transformers;

import com.example.QuickPick.dto.request.CustomerRequest;
import com.example.QuickPick.dto.response.CustomerResponse;
import com.example.QuickPick.models.Customer;

public class CustomerTransformer {
    public static Customer customerRequestToCustomer(CustomerRequest customerRequest){
        Customer customer= Customer.builder()
                .email(customerRequest.getEmail())
                .name(customerRequest.getName())
                .address(customerRequest.getAddress())
                .gender(customerRequest.getGender())
                .age(customerRequest.getAge())
                .build();
        return customer;
    }

    public  static CustomerResponse customerToCustomerResponse(Customer customer){
        return CustomerResponse.builder().name(customer.getName()).email(customer.getEmail()).build();
    }
}
