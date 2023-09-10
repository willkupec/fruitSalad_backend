package com.fruitSalad_backend.Backend.payment.repository;

import com.fruitSalad_backend.Backend.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    public Payment findById(int id);
}
