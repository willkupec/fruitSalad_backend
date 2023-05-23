package com.fruitSalad_backend.Backend.service;

import com.fruitSalad_backend.Backend.model.Customer;

import java.util.List;

public interface ICustomerService {
    public Customer registerCustomer(Customer customer);
    public Customer loginCustomer(Customer customer);
    public List<Customer> getAllCustomers();
}
