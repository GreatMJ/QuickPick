package com.example.QuickPick.transformers;

import com.example.QuickPick.dto.request.CabRequest;
import com.example.QuickPick.dto.response.CabResponse;
import com.example.QuickPick.models.Cab;

public class CabTransformer {
    public static Cab cabRequestToCab(CabRequest cabRequest){

        return Cab.builder()
                .cabNo(cabRequest.getCabNo())
                .company(cabRequest.getCompany())
                .carModel(cabRequest.getCarModel())
                .farePerKm(cabRequest.getFarePerKm())
                .numberOfSeats(cabRequest.getNumberOfSeats())
                .carType(cabRequest.getCarType())
                .available(true)
                .build();
    }

    public static CabResponse cabToCabResponse(Cab cab){
        return CabResponse.builder()
                .cabNo(cab.getCabNo())
                .carModel(cab.getCarModel())
                .farePerKm(cab.getFarePerKm())
                .build();
    }
}
