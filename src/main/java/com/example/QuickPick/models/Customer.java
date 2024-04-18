package com.example.QuickPick.models;

import com.example.QuickPick.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "customer")
@Builder
@ToString
public class Customer {

    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

   String name;

   @Column(nullable = false,unique = true)
    String email;

    int age;
    String address;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<TripBooking> bookings;

}
