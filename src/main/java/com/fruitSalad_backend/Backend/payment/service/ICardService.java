package com.fruitSalad_backend.Backend.payment.service;

import com.fruitSalad_backend.Backend.payment.model.Card;
import java.util.List;

public interface ICardService {
    public Card addCard(Card card);
    public void removeCard(int id);
    public List<Card> updateCard(Card card);
    public List<Card> getAllCards();
    public Card getCardById(int id);
}
