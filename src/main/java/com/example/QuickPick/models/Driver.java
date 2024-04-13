package com.example.QuickPick.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
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
}
