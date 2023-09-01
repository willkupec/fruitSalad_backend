package com.fruitSalad_backend.Backend.customer.repository;

import com.fruitSalad_backend.Backend.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Customer findById(int id);
    public Customer findByEmail(String email);
}
