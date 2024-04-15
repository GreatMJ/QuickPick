package com.example.QuickPick.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Driver {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    int age;
    String panNumber;
    @Column(nullable = false,unique = true)
    String mobNo;

    double rating;

    @OneToOne(mappedBy = "driver",cascade = CascadeType.ALL)
    Cab cab;

    @OneToMany(mappedBy = "driver",cascade = CascadeType.ALL)
    List<TripBooking> trips;
}
