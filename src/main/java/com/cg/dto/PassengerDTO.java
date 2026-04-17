package com.cg.dto;

public class PassengerDTO {

    private String passengerName;
    private int passengerAge;
    private String seatNo;

    public PassengerDTO() {}

    public PassengerDTO(String passengerName, int passengerAge, String seatNo) {
        this.passengerName = passengerName;
        this.passengerAge = passengerAge;
        this.seatNo = seatNo;
    }

    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }

    public int getPassengerAge() { return passengerAge; }
    public void setPassengerAge(int passengerAge) { this.passengerAge = passengerAge; }

    public String getSeatNo() { return seatNo; }
    public void setSeatNo(String seatNo) { this.seatNo = seatNo; }
}