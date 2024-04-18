package com.example.QuickPick.models;

import com.example.QuickPick.enums.TripStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TripBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String  bookingId;
    String pickUp;
    String destination;
    double tripDistanceInKm;
    double totalFare;
    @Enumerated(EnumType.STRING)
    TripStatus tripStatus;
    @CreationTimestamp
    Date bookedOn;

    @ManyToOne
            @JoinColumn(name = "customer_id")
    Customer customer;

    @ManyToOne
            @JoinColumn(name = "driver_id")
    Driver driver;
}
