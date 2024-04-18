package com.example.QuickPick.dto.response;

import com.example.QuickPick.enums.TripStatus;


import java.util.Date;


import  lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TripResponse {
    String  bookingId;
    String pickUp;
    String destination;
    double tripDistanceInKm;
    double totalFare;

    TripStatus tripStatus;

    Date bookedOn;

    CustomerResponse customerResponse;

    DriverResponse driverResponse;
}
