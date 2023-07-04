package com.fruitSalad_backend.Backend.controller;


import com.fruitSalad_backend.Backend.dto.AuthResponseDto;
import com.fruitSalad_backend.Backend.dto.LoginDto;
import com.fruitSalad_backend.Backend.dto.RegisterDto;
import com.fruitSalad_backend.Backend.model.cartItem.CartItem;
import com.fruitSalad_backend.Backend.model.customer.Customer;
import com.fruitSalad_backend.Backend.repository.CustomerRepository;
import com.fruitSalad_backend.Backend.security.JwtGenerator;
import com.fruitSalad_backend.Backend.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private ICustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (customerRepository.findByEmail(registerDto.getEmail()) != null) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        Customer customer = new Customer();
        customer.setFirstName(registerDto.getFirstName());
        customer.setLastName(registerDto.getLastName());
        customer.setEmail(registerDto.getEmail());
        customer.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        customerRepository.save(customer);

        return new ResponseEntity<>("User registered!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }

    @GetMapping("")
    public List<Customer> list() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable("id") int id) {
        return customerService.getCustomerById(id);
    }

/*    @GetMapping("/{id}")
    public ArrayList<CartItem> listCart() {
        return customerService.getCart();
    }*/

}
