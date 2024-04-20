package com.example.QuickPick.controllers;

import com.example.QuickPick.dto.request.TripRequest;
import com.example.QuickPick.dto.response.TripResponse;
import com.example.QuickPick.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/trip")
@RequiredArgsConstructor
public class TripController {
    private  final TripService tripService;

    @PostMapping("/book")
    public ResponseEntity<TripResponse> bookCab(@RequestBody TripRequest tripRequest,@RequestParam(value = "applyCoupon",required = false,defaultValue = "false") boolean applyCoupon){
      TripResponse tripResponse=  tripService.bookCab(tripRequest,applyCoupon);
        return new ResponseEntity<>(tripResponse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> markTripStatusCompleted(@RequestParam("bookingId")String bookingId,@RequestParam(value = "rating",required = false,defaultValue = "0")double rating){
        String response=tripService.markTripCompleted(bookingId,rating);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }



}
