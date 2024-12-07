package com.example.QuickPick.dto.request;

import com.example.QuickPick.enums.CarType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CabRequest  {

    @NotBlank(message = "Company name is required.")
    String company;

    @NotBlank(message = "Car model is required.")
    String carModel;

    @NotBlank(message = "Car number is required.")
    String cabNo;

    @NotNull(message = "CarType is required.")
    CarType carType;

    @Positive(message = "The number of seats must be greater than zero.")
    int numberOfSeats;

    @Positive(message = "Fare per kilometer must be greater than zero.")
    double farePerKm;
}
