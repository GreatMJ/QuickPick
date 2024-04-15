package com.example.QuickPick.models;

import com.example.QuickPick.enums.CarType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Cab {
@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String company;
    String carModel;
   @Enumerated(EnumType.STRING)
    CarType carType;
   int numberOfSeats;
   String cabNo;

   double farePerKm;
@OneToOne
@JoinColumn(name = "driver_id")  // mention join column annotation in table in which you wanna have join column
  Driver driver;

}
