package com.fruitSalad_backend.Backend.payment.service;

import com.fruitSalad_backend.Backend.payment.model.Card;
import com.fruitSalad_backend.Backend.payment.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService implements ICardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card addCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public void removeCard(int id) {
        cardRepository.deleteById(id);
    }

    @Override
    public List<Card> updateCard(Card card) {
        return cardRepository.findAll();
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Card getCardById(int id) {
        return cardRepository.findById(id);
    }
}
