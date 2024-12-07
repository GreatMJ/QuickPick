package com.example.QuickPick.dto.request;


import com.example.QuickPick.enums.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequest {

    @NotBlank(message = "Name is required.")
    String name;


    @NotBlank(message = "Email is required.")
    @Email(message = "Add valid email.")
    String email;

    @Positive(message = "Age should be greater than zero.")
    int age;

    String address;

    @NotNull(message = "Gender is required.")
    Gender gender;
}
