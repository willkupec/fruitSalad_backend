package com.fruitSalad_backend.Backend.repository;

import com.fruitSalad_backend.Backend.model.cartItem.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    public CartItem findById(int id);
    public CartItem findByTitle(String title);
}
