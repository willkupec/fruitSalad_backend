package com.fruitSalad_backend.Backend.cart.repository;

import com.fruitSalad_backend.Backend.cart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Integer> {
    public CartItem findById(int id);
    public CartItem findByTitle(String title);
}
