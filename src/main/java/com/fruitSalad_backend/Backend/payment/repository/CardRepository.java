package com.fruitSalad_backend.Backend.payment.repository;

import com.fruitSalad_backend.Backend.payment.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    public Card findById(int id);
}
