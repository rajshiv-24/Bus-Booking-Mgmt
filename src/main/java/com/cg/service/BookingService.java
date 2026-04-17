package com.cg.service;

import com.cg.dto.BookingRequest;
import com.cg.dto.PassengerDTO;
import com.cg.entity.*;
import com.cg.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    @Autowired private BusBookingRepository bookingRepository;
    @Autowired private RouteScheduleRepository scheduleRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private PassengerRepository passengerRepository;

    @Transactional
    public BusBooking bookSeat(BookingRequest request) {
        RouteSchedule schedule = scheduleRepository.findById(request.getScheduleId())
            .orElseThrow(() -> new RuntimeException("Schedule not found"));

        if (schedule.getAvlSeats() < request.getPassengers().size()) {
            throw new RuntimeException("Not enough seats available");
        }

        Customer customer = customerRepository.findById(request.getCustId())
            .orElseThrow(() -> new RuntimeException("Customer not found"));

        BusBooking booking = new BusBooking();
        booking.setSchedule(schedule);
        booking.setCustomer(customer);
        booking.setBookingDt(LocalDate.now());
        booking.setBookingStatus("CONFIRMED");
        booking = bookingRepository.save(booking);

        List<Passenger> passengers = new ArrayList<>();
        for (PassengerDTO dto : request.getPassengers()) {
            Passenger p = new Passenger();
            p.setPassengerName(dto.getPassengerName());
            p.setPassengerAge(dto.getPassengerAge());
            p.setSeatNo(dto.getSeatNo());
            p.setBooking(booking);
            passengers.add(p);
        }
        passengerRepository.saveAll(passengers);

        schedule.setAvlSeats(schedule.getAvlSeats() - request.getPassengers().size());
        scheduleRepository.save(schedule);

        return booking;
    }

    public List<BusBooking> getBookingsByCustomer(Long custId) {
        return bookingRepository.findByCustomerCustId(custId);
    }

    public List<Passenger> getPassengersByBooking(Long bookingId) {
        return passengerRepository.findByBookingId(bookingId);
    }
}