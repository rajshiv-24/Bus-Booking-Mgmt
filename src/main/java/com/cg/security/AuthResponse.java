package com.cg.security;

public class AuthResponse {

    private String token;
    private Long custId;
    private String custName;

    public AuthResponse() {}

    public AuthResponse(String token, Long custId, String custName) {
        this.token = token;
        this.custId = custId;
        this.custName = custName;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Long getCustId() { return custId; }
    public void setCustId(Long custId) { this.custId = custId; }

    public String getCustName() { return custName; }
    public void setCustName(String custName) { this.custName = custName; }
}