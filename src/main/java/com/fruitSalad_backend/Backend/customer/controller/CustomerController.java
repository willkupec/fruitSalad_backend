package com.fruitSalad_backend.Backend.customer.controller;


import com.fruitSalad_backend.Backend.customer.model.Customer;
import com.fruitSalad_backend.Backend.customer.repository.CustomerRepository;
import com.fruitSalad_backend.Backend.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ICustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Customer customer) {
        if (customerRepository.findByEmail(customer.getEmail()) != null) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        customerRepository.save(customer);

        return new ResponseEntity<>("User registered!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Customer customer) {

        String email = customer.getEmail();

        if (customerRepository.findByEmail(email) == null) {
            return new ResponseEntity<String>("Customer doesn't exist", HttpStatus.NOT_FOUND);
        }

        Customer foundCustomer = customerRepository.findByEmail(email);

        if (foundCustomer != null) {
            return new ResponseEntity<Customer>(foundCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unable to login.", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("")
    public List<Customer> list() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable("id") int id) {
        return customerService.getCustomerById(id);
    }

}
