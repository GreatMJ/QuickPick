package com.example.QuickPick.service;

import com.example.QuickPick.dto.request.TripRequest;
import com.example.QuickPick.dto.response.TripResponse;
import com.example.QuickPick.enums.TripStatus;
import com.example.QuickPick.exception.CustomerNotFoundException;
import com.example.QuickPick.exception.ResourceNotFoundException;
import com.example.QuickPick.models.*;
import com.example.QuickPick.repository.*;
import com.example.QuickPick.transformers.TripBookingTransfomer;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;   // constructor injection

    private final CustomerRepository customerRepository;

    private final CabRepository cabRepository;

    private  final DriverRepository driverRepository;
    private  final CouponRepository couponRepository;

    private final JavaMailSender javaMailSender;


    // book Cab
    public TripResponse bookCab(TripRequest tripRequest,boolean applyCoupon){

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
        // apply coupon if can be applied
        if(applyCoupon){
            Coupon coupon=couponRepository.getRandomCoupon();
         if(coupon!=null){
             totalFare=totalFare-(totalFare*coupon.getPercentageDiscount())/100;
             couponRepository.delete(coupon);
         }

        }
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

        // send mail
        sendMail(savedTrip);

       return TripBookingTransfomer.tripToTripResponse(trip);

    }

    // send mail
    private void sendMail(TripBooking tripBooking){


        String messageBody = "Congratulations! Your ride has been successfully booked. Here are the details:\n" +
                "Booking ID: **" + tripBooking.getBookingId() + "**\n" +
                "Pickup Point: "+tripBooking.getPickUp()+"\n"+
                "Destination: **" + tripBooking.getDestination() + "**\n" +
                "Distance: **" + tripBooking.getTripDistanceInKm() + " km**\n" +
                "Total Fare: **$" + tripBooking.getTotalFare() + "**\n" +
                "Driver: **" + tripBooking.getDriver().getName() + "**\n" +
                "Booked on: **" + tripBooking.getBookedOn() + "**\n" +
                "Your driver will arrive shortly. Have a safe journey with us!";

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("acciojobspring@gmail.com");
        simpleMailMessage.setTo(tripBooking.getCustomer().getEmail());
        simpleMailMessage.setSubject("Your ride is succesfully booked with RideIt!");
        simpleMailMessage.setText(messageBody);
        javaMailSender.send(simpleMailMessage);

    }

    // mark trip status as completed by taking bookingId as input

    public String markTripCompleted(String bookingId,double rating){
        // first check weather the bookingId is valid or not
        TripBooking trip=tripRepository.findByBookingId(bookingId);
        if(trip==null){
            throw new ResourceNotFoundException("Invalid booking id.");
        }

        // mark as completed
        trip.setTripStatus(TripStatus.COMPLETED);
        tripRepository.save(trip);
        // make this cab availabe now
      Cab cab= trip.getDriver().getCab();
      cab.setAvailable(true);
      cabRepository.save(cab);
      // rate driver
        if(rating!=0){
            Driver driver=trip.getDriver();
            driver.setRating(driver.getRating()+rating);
            driverRepository.save(driver);
        }

        return "Your trip is complete. Thank you for riding with us!";
    }



}
