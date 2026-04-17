package com.cg.dto;

public class LoginRequest {

    private String phoneNo;
    private String custName;

    public LoginRequest() {}

    public LoginRequest(String phoneNo, String custName) {
        this.phoneNo = phoneNo;
        this.custName = custName;
    }

    public String getPhoneNo() { return phoneNo; }
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }

    public String getCustName() { return custName; }
    public void setCustName(String custName) { this.custName = custName; }
}