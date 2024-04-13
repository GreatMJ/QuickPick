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
public class Cab {
@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String company;
    String carModel;
   @Enumerated(EnumType.STRING)
    CarType carType;
   int numberOfSeats;

   double farePerKm;
@OneToOne(mappedBy = "cab",cascade = CascadeType.ALL)
  Driver driver;

}
