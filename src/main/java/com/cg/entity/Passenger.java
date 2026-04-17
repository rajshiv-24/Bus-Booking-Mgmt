package com.cg.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "passenger")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passengerName;
    private int passengerAge;
    private String seatNo;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private BusBooking booking;

    public Passenger() {}

    public Passenger(Long id, String passengerName, int passengerAge,
                     String seatNo, BusBooking booking) {
        this.id = id;
        this.passengerName = passengerName;
        this.passengerAge = passengerAge;
        this.seatNo = seatNo;
        this.booking = booking;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }

    public int getPassengerAge() { return passengerAge; }
    public void setPassengerAge(int passengerAge) { this.passengerAge = passengerAge; }

    public String getSeatNo() { return seatNo; }
    public void setSeatNo(String seatNo) { this.seatNo = seatNo; }

    public BusBooking getBooking() { return booking; }
    public void setBooking(BusBooking booking) { this.booking = booking; }
}