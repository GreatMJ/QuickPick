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
public class TripBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String source;
    String destination;
    double tripDistanceInKm;
    double totalFare;
    @Enumerated(EnumType.STRING)
    TripStatus tripStatus;
    @CreationTimestamp
    Date bookedOn;

    @ManyToOne
    Customer customer;
}
