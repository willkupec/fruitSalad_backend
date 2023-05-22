package com.fruitSalad_backend.Backend.repository;

import com.fruitSalad_backend.Backend.model.CustomerImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerImpl, Integer> {
    public CustomerImpl findByEmail(String email);
}
