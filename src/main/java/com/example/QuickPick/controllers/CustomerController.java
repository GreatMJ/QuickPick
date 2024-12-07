package com.example.QuickPick.controllers;

import com.example.QuickPick.dto.request.CustomerRequest;
import com.example.QuickPick.dto.response.CustomerResponse;
import com.example.QuickPick.enums.Gender;
import com.example.QuickPick.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@Validated
public class CustomerController {



    private  final  CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping
    public ResponseEntity<CustomerResponse> addCustomer( @Valid  @RequestBody CustomerRequest customerRequest){
         CustomerResponse customerResponse= customerService.addCustomer(customerRequest);
          return new  ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable @NotNull(message = "Id is required.") @Positive(message = "Id can not be negative") Integer id){
        CustomerResponse customerResponse=customerService.getById(id);
        return ResponseEntity.ok(customerResponse);
    }


    @GetMapping("/all")
    public ResponseEntity<List<CustomerResponse>> getAll(){
        List<CustomerResponse> customerResponses=customerService.getAll();
        return ResponseEntity.ok(customerResponses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @NotNull(message = "Id is required.") @Positive(message = "Id can not be negative") Integer id){
        customerService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/gender/{gender}/age/{age}")
    public ResponseEntity<List<CustomerResponse>> getCustomerByGenderAndAgeGreaterThan(@PathVariable("gender") @NotNull(message = "Please provide gender.") Gender gender, @PathVariable(value = "age") @Positive(message = "Age should be greater than zero.") int age){
        List<CustomerResponse> customerResponses=customerService.getCustomerByGenderAndAgeGreaterThan(gender,age);
        return new ResponseEntity<>(customerResponses,HttpStatus.FOUND);
    }

}
