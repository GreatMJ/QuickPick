package com.example.QuickPick.transformers;

import com.example.QuickPick.dto.request.TripRequest;
import com.example.QuickPick.dto.response.CustomerResponse;
import com.example.QuickPick.dto.response.DriverResponse;
import com.example.QuickPick.dto.response.TripResponse;
import com.example.QuickPick.enums.TripStatus;
import com.example.QuickPick.models.TripBooking;

import java.util.Date;
import java.util.UUID;

public class TripBookingTransfomer {

    public static TripBooking tripRequestToTripBooking(TripRequest tripRequest){
        return TripBooking.builder()
                .bookingId(UUID.randomUUID().toString())
                .pickUp(tripRequest.getPickUp())
                .destination(tripRequest.getDestination())
                .tripStatus(TripStatus.IN_TRANSIT)
                .build();
    }


   public static TripResponse tripToTripResponse(TripBooking trip){
        return TripResponse.builder()
                .bookingId(trip.getBookingId())
                .pickUp(trip.getPickUp())
                .destination(trip.getDestination())
                .tripDistanceInKm(trip.getTripDistanceInKm())
                .totalFare(trip.getTotalFare())
                .tripStatus(trip.getTripStatus())
                .bookedOn(trip.getBookedOn())
                .customerResponse(CustomerTransformer.customerToCustomerResponse(trip.getCustomer()))
                .driverResponse(DriverTransoformer.driverToDriverResponse(trip.getDriver()))
                .build();
   }

}
