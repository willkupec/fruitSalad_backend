package com.fruitSalad_backend.Backend.checkout.repository;

import com.fruitSalad_backend.Backend.checkout.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<Address, Integer> {
    public Address findById(int id);
    public Address findByTitle(String title);
}
