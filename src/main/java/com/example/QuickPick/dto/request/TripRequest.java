package com.example.QuickPick.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TripRequest {

    String pickUp;
    String destination;
    String customerEmail;

}
