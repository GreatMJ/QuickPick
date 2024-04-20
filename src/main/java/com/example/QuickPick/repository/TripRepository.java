package com.example.QuickPick.repository;

import com.example.QuickPick.models.TripBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<TripBooking,Integer> {
    public TripBooking findByBookingId(String bookingId);
}
