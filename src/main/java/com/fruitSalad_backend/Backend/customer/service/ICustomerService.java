package com.fruitSalad_backend.Backend.customer.service;

import com.fruitSalad_backend.Backend.customer.model.Customer;

import java.util.List;

public interface ICustomerService {
    public Customer registerCustomer(Customer customer);
    public Customer loginCustomer(Customer customer);
    public void removeCustomer(int id);
    public Customer updateCustomer(Customer customer);
    public List<Customer> getAllCustomers();
    public Customer getCustomerById(int id);
}
