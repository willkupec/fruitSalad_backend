package com.fruitSalad_backend.Backend.service;

import com.fruitSalad_backend.Backend.model.CustomerImpl;
import com.fruitSalad_backend.Backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public CustomerImpl registerCustomer(CustomerImpl customer) {
        return customerRepository.save(customer);
    }

    @Override
    public CustomerImpl loginCustomer(CustomerImpl customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<CustomerImpl> getAllCustomers() {
        return customerRepository.findAll();
    }
}
