package com.cg.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long custId;

    private String custName;
    private String phoneNo;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<BusBooking> bookings;

    public Customer() {}

    public Customer(Long custId, String custName, String phoneNo, List<BusBooking> bookings) {
        this.custId = custId;
        this.custName = custName;
        this.phoneNo = phoneNo;
        this.bookings = bookings;
    }//mai aarha hu ...this is my final code

    public Long getCustId() { return custId; }
    public void setCustId(Long custId) { this.custId = custId; }

    public String getCustName() { return custName; }
    public void setCustName(String custName) { this.custName = custName; }

    public String getPhoneNo() { return phoneNo; }
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }

    public List<BusBooking> getBookings() { return bookings; }
    public void setBookings(List<BusBooking> bookings) { this.bookings = bookings; }
}