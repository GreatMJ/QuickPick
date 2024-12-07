package com.example.QuickPick.dto.request;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@ToString
public class DriverRequest {

    @NotBlank(message = "Name is required.")
    String name;

    @Positive(message = "Age should be greater than zero.")
    int age;

    @NotBlank(message = "Pan Number is required")
            @Size( min = 10,max =10, message = "It should be alphanumeric with length strictly of 10")
    String panNumber;


    @NotBlank(message = "Number is required")
    @Size( min = 10,max =10, message = "Please provide valid contact details.")
    String mobNo;

 @NotNull(message = "Cab details are required.")
         @Valid
    CabRequest cab;
}
