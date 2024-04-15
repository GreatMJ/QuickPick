package com.example.QuickPick.dto.request;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class DriverRequest {
    String name;
    int age;
    String panNumber;

    String mobNo;

    CabRequest cab;
}
