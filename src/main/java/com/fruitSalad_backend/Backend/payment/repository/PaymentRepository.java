package com.fruitSalad_backend.Backend.payment.repository;

import com.fruitSalad_backend.Backend.payment.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Order, Integer> {
    public Order findById(int id);
}
