package com.cg.controller;

import com.cg.dto.BookingRequest;
import com.cg.entity.BusBooking;
import com.cg.entity.Passenger;
import com.cg.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<?> bookSeat(@RequestBody BookingRequest request) {
        try {
            BusBooking booking = bookingService.bookSeat(request);
            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/customer/{custId}")
    public ResponseEntity<List<BusBooking>> getBookingsByCustomer(@PathVariable Long custId) {
        return ResponseEntity.ok(bookingService.getBookingsByCustomer(custId));
    }

    @GetMapping("/{bookingId}/passengers")
    public ResponseEntity<List<Passenger>> getPassengers(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.getPassengersByBooking(bookingId));
    }
}