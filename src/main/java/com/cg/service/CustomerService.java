package com.cg.service;

import com.cg.dto.LoginRequest;
import com.cg.entity.Customer;
import com.cg.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer login(LoginRequest request) {
        Optional<Customer> customer = customerRepository.findByPhoneNo(request.getPhoneNo());
        if (customer.isPresent() &&
            customer.get().getCustName().equalsIgnoreCase(request.getCustName())) {
            return customer.get();
        }
        throw new RuntimeException("Invalid credentials");
    }
}