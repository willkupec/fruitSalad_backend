package com.fruitSalad_backend.checkout.repository;

import com.fruitSalad_backend.checkout.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutRepository extends JpaRepository<Address, Integer> {
    public Address findById(int id);
    public Address findByCustomer(String customer);
}
