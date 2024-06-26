package com.example.QuickPick.controllers;

import com.example.QuickPick.dto.request.CustomerRequest;
import com.example.QuickPick.dto.response.CustomerResponse;
import com.example.QuickPick.enums.Gender;
import com.example.QuickPick.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

//    @Autowired
//    CustomerService customerService;

    private  final  CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping
    public ResponseEntity<CustomerResponse> addCustomer(@RequestBody CustomerRequest customerRequest){
         CustomerResponse customerResponse= customerService.addCustomer(customerRequest);
          return new  ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }
    @GetMapping("/gender/{gender}/age/{age}")
    public ResponseEntity<List<CustomerResponse>> getCustomerByGenderAndAgeGreaterThan(@PathVariable("gender") Gender gender,@PathVariable(value = "age")int age){
           List<CustomerResponse> customerResponses=customerService.getCustomerByGenderAndAgeGreaterThan(gender,age);
           return new ResponseEntity<>(customerResponses,HttpStatus.FOUND);
    }

}
