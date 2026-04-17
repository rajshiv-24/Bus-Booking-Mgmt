package com.cg.dto;

import java.util.List;

public class BookingRequest {

    private Long scheduleId;
    private Long custId;
    private List<PassengerDTO> passengers;

    public BookingRequest() {}

    public BookingRequest(Long scheduleId, Long custId, List<PassengerDTO> passengers) {
        this.scheduleId = scheduleId;
        this.custId = custId;
        this.passengers = passengers;
    }

    public Long getScheduleId() { return scheduleId; }
    public void setScheduleId(Long scheduleId) { this.scheduleId = scheduleId; }

    public Long getCustId() { return custId; }
    public void setCustId(Long custId) { this.custId = custId; }

    public List<PassengerDTO> getPassengers() { return passengers; }
    public void setPassengers(List<PassengerDTO> passengers) { this.passengers = passengers; }
}