package com.fruitSalad_backend.Backend.service;

import com.fruitSalad_backend.Backend.model.CustomerImpl;

import java.util.List;

public interface CustomerService {
    public CustomerImpl registerCustomer(CustomerImpl customer);
    public CustomerImpl loginCustomer(CustomerImpl customer);
    public List<CustomerImpl> getAllCustomers();
}
