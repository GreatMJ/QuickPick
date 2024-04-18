package com.example.QuickPick.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CabResponse {
    String cabNo;

    String carModel;

    double farePerKm;
}
