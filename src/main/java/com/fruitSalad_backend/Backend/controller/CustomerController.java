package com.fruitSalad_backend.Backend.controller;


import com.fruitSalad_backend.Backend.model.Customer;
import com.fruitSalad_backend.Backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public String register(@RequestBody Customer customer) {
        customerService.registerCustomer(customer);
        return "New customer registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody Customer customer) {
        return "customerService.loginCustomer(customer)";
    }

    @GetMapping("")
    public List<Customer> list(){
        return customerService.getAllCustomers();
    }

}
