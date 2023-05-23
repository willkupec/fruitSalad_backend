package com.fruitSalad_backend.Backend.security;

import com.fruitSalad_backend.Backend.model.CustomerDetails;
import com.fruitSalad_backend.Backend.model.Customer;
import com.fruitSalad_backend.Backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    public CustomerDetailsService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public CustomerDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepo.findByEmail(username);
        if (customer == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomerDetails(customer);
    }
}
