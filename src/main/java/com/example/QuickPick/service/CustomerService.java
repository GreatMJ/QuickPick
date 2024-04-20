package com.example.QuickPick.service;

import com.example.QuickPick.dto.request.CustomerRequest;
import com.example.QuickPick.dto.response.CustomerResponse;
import com.example.QuickPick.enums.Gender;
import com.example.QuickPick.models.Customer;
import com.example.QuickPick.repository.CustomerRepository;
import com.example.QuickPick.transformers.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

//    @Autowired
//    CustomerRepository customerRepository;   field injection

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {    // constructior injection
        this.customerRepository = customerRepository;
    }



    // add customer
    public CustomerResponse addCustomer(CustomerRequest customerRequest){
        // dto-->entity

        Customer customer= CustomerTransformer.customerRequestToCustomer(customerRequest);

        Customer savedCustomer=customerRepository.save(customer);
        return CustomerTransformer.customerToCustomerResponse(savedCustomer);
    }

    // get customer with giver constraints
    public List<CustomerResponse> getCustomerByGenderAndAgeGreaterThan(Gender gender, int age){
        List<Customer> customers=customerRepository.getCustomerByGenderAndAgeGreaterThan( gender, age);

        List<CustomerResponse> customerResponses=new ArrayList<>();
        for(Customer customer :customers){
            CustomerResponse customerResponse=CustomerTransformer.customerToCustomerResponse(customer);
            customerResponses.add(customerResponse);
        }

        return  customerResponses;
    }



}
