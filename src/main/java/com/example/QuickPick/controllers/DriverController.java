package com.example.QuickPick.controllers;

import com.example.QuickPick.dto.request.DriverRequest;
import com.example.QuickPick.dto.response.DriverResponse;
import com.example.QuickPick.exception.ResourceNotFoundException;
import com.example.QuickPick.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/driver")
public class DriverController {
//    @Autowired
//    DriverService driverService;
    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }


    @PostMapping
    public ResponseEntity<String> addDriver(@RequestBody DriverRequest driverRequest){
       String response= driverService.addDriver(driverRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteDriver(@RequestParam String mobNo){
        DriverResponse driverResponse = null;
        try{
           driverResponse=driverService.deleteDriverByMobileNumber(mobNo);
        }catch (ResourceNotFoundException resourceNotFoundException){
          String errorMessage=resourceNotFoundException.getMessage();

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);


        }

        return new ResponseEntity<>("Driver successfully deleted.",HttpStatus.OK);
    }



}
