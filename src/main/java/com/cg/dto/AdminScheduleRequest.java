package com.cg.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AdminScheduleRequest {

    private Long routeId;
    private LocalTime departureTime;
    private LocalDate scheduleDt;
    private int avlSeats;
    private int totSeats;
    private String schStatus;

    public AdminScheduleRequest() {}

    public Long getRouteId() { return routeId; }
    public void setRouteId(Long routeId) { this.routeId = routeId; }

    public LocalTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalTime departureTime) { this.departureTime = departureTime; }

    public LocalDate getScheduleDt() { return scheduleDt; }
    public void setScheduleDt(LocalDate scheduleDt) { this.scheduleDt = scheduleDt; }

    public int getAvlSeats() { return avlSeats; }
    public void setAvlSeats(int avlSeats) { this.avlSeats = avlSeats; }

    public int getTotSeats() { return totSeats; }
    public void setTotSeats(int totSeats) { this.totSeats = totSeats; }

    public String getSchStatus() { return schStatus; }
    public void setSchStatus(String schStatus) { this.schStatus = schStatus; }
}