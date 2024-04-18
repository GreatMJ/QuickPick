package com.example.QuickPick.service;

import com.example.QuickPick.dto.request.TripRequest;
import com.example.QuickPick.dto.response.TripResponse;
import com.example.QuickPick.exception.CustomerNotFoundException;
import com.example.QuickPick.exception.ResourceNotFoundException;
import com.example.QuickPick.models.Cab;
import com.example.QuickPick.models.Customer;
import com.example.QuickPick.models.TripBooking;
import com.example.QuickPick.repository.CabRepository;
import com.example.QuickPick.repository.CustomerRepository;
import com.example.QuickPick.repository.DriverRepository;
import com.example.QuickPick.repository.TripRepository;
import com.example.QuickPick.transformers.TripBookingTransfomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;   // constructor injection

    private final CustomerRepository customerRepository;

    private final CabRepository cabRepository;

    private  final DriverRepository driverRepository;

    public TripResponse bookCab(TripRequest tripRequest){

        // first check weather customer with given email id have registered or not
        Customer customer=customerRepository.findByEmail(tripRequest.getCustomerEmail());

        if(customer==null){
            throw new CustomerNotFoundException("Customer with given email id not exist in the system.");
        }

        // now look for cab availability
        Cab cab=cabRepository.getAvailableCab();

        if(cab==null){
            throw new ResourceNotFoundException("Sorry! All drivers are busy right now!!");
        }

        // now book the trip

        // let me make the transformers
        TripBooking trip= TripBookingTransfomer.tripRequestToTripBooking(tripRequest);

        // calculate totalFare and totalDistance
        int totalDistance=(int)(Math.random()*100)+1;
        double totalFare=cab.getFarePerKm()*totalDistance;
        // set the remaining attributes
        trip.setTotalFare(totalFare);
        trip.setTripDistanceInKm(totalDistance);
        trip.setCustomer(customer);
        trip.setDriver(cab.getDriver());

        // save the booking
       TripBooking savedTrip= tripRepository.save(trip);

       // update changes that happend in related entities
        customer.getBookings().add(savedTrip);
        cab.setAvailable(false);
        cab.getDriver().getTrips().add(savedTrip);

        // customer and booking
        customerRepository.save(customer); // customer + savedBooking
        driverRepository.save(cab.getDriver()); // driver + cab + savedBooking

       return TripBookingTransfomer.tripToTripResponse(trip);

    }



}
