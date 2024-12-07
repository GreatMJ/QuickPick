package com.example.QuickPick.service;

import com.example.QuickPick.dto.request.CustomerRequest;
import com.example.QuickPick.dto.response.CustomerResponse;
import com.example.QuickPick.enums.Gender;
import com.example.QuickPick.exception.ResourceNotFoundException;
import com.example.QuickPick.models.Customer;
import com.example.QuickPick.repository.CustomerRepository;
import com.example.QuickPick.transformers.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

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

        // handle empty list
        if(customers==null||customers.isEmpty()) throw new ResourceNotFoundException("No customers found matching the selected filter criteria.");

        // initialize the list
        List<CustomerResponse> customerResponses=new ArrayList<>(customers.size());
        for(Customer customer :customers){
            CustomerResponse customerResponse=CustomerTransformer.customerToCustomerResponse(customer);
            customerResponses.add(customerResponse);
        }

        return  customerResponses;
    }

    // method to get customer by id
    public CustomerResponse getById(Integer id){
        Customer customer=customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("Customer with id: %d not found.",id)));
        return CustomerTransformer.customerToCustomerResponse(customer);
    }

    // method to get all customers
    public List<CustomerResponse> getAll(){
        List<Customer> customers=customerRepository.findAll();
        // handle empty list
        if(customers==null||customers.isEmpty()) throw new ResourceNotFoundException("No customers found.");

        // initialize the list of customerResponse
        List<CustomerResponse> customerResponses=new ArrayList<>(customers.size());
        for(Customer customer:customers){
            CustomerResponse customerResponse=CustomerTransformer.customerToCustomerResponse(customer);
            customerResponses.add(customerResponse);
        }

        return customerResponses;
    }


    // method to delete the customer by id
    public void deleteById(Integer id){
        Customer customer=customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("Customer with id: %d not found.",id)));
       customerRepository.delete(customer);
       return;
    }



}
