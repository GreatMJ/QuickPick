package com.example.QuickPick.dto.request;


import com.example.QuickPick.enums.Gender;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequest {
    String name;


    String email;

    int age;
    String address;

    Gender gender;
}
