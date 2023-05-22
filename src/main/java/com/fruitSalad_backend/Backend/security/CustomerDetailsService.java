package com.fruitSalad_backend.Backend.security;

import com.fruitSalad_backend.Backend.model.CustomerDetails;
import com.fruitSalad_backend.Backend.model.CustomerImpl;
import com.fruitSalad_backend.Backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomerDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepo;

    @Override
    public CustomerDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomerImpl customer = customerRepo.findByEmail(username);
        if (customer == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomerDetails(customer);
    }
}
