package com.example.QuickPick.controllers;

import com.example.QuickPick.dto.request.TripRequest;
import com.example.QuickPick.dto.response.TripResponse;
import com.example.QuickPick.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/trip")
@RequiredArgsConstructor
public class TripController {
    private  final TripService tripService;

    @PostMapping("/book")
    public ResponseEntity<TripResponse> bookCab(@RequestBody TripRequest tripRequest){
      TripResponse tripResponse=  tripService.bookCab(tripRequest);
        return new ResponseEntity<>(tripResponse, HttpStatus.CREATED);
    }



}
