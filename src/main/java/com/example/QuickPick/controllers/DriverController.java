package com.example.QuickPick.controllers;

import com.example.QuickPick.dto.request.DriverRequest;
import com.example.QuickPick.dto.response.DriverResponse;
import com.example.QuickPick.exception.ResourceNotFoundException;
import com.example.QuickPick.service.DriverService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/driver")
@Validated
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }


    @PostMapping
    public ResponseEntity<String> addDriver(@Valid @RequestBody DriverRequest driverRequest){
       String response= driverService.addDriver(driverRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/age")
    public ResponseEntity<List<DriverResponse>> getDriverWithAgeGreaterThan(@RequestParam @NotNull(message = "Age is required.") @Min(value = 18,message = "Age should be greater than or equal to 18.") Integer age){
        List<DriverResponse> driverResponses=driverService.getDriverWithAgeGreaterThan(age);
        return  ResponseEntity.ok(driverResponses);
    }





}
