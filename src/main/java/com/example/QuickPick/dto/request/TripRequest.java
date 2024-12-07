package com.example.QuickPick.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TripRequest {

    @NotBlank(message = "Please provide pickup location.")
    String pickUp;
    @NotBlank(message = "Please provide destination.")
    String destination;
    @NotBlank(message = "Please provide your email.")
            @Email(message = "Enter valid email address.")
    String customerEmail;

}
