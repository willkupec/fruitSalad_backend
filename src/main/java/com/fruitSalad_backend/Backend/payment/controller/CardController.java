package com.fruitSalad_backend.Backend.payment.controller;

import com.fruitSalad_backend.Backend.payment.messaging.CardProducer;
import com.fruitSalad_backend.Backend.payment.model.Card;
import com.fruitSalad_backend.Backend.payment.repository.CardRepository;
import com.fruitSalad_backend.Backend.payment.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RequestMapping("/card")
@RestController
@CrossOrigin
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ICardService cardService;

    @Autowired
    CardProducer cardProducer;

    @PostMapping("")
    public String add(@RequestBody Card card) {
        try {
            cardProducer.sendMessage("Added card");
        } catch (Exception e) {
            System.out.println(e);
        }
        cardService.addCard(card);
        return "Added " + card.toString();
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") int id) {
        try {
            cardProducer.sendMessage("Removed card");
        } catch (Exception e) {
            System.out.println(e);
        }
        cardService.removeCard(id);
        return "Removed card with id: " + id;
    }

    @PutMapping("/{id}")
    @Transactional
    public List<Card> update(@PathVariable int id, @RequestBody Card card) throws Exception {
        Card existingCard = cardService.getCardById(id);

        if (existingCard == null) {
            throw new Exception("Card Not Found");
        }

        String name = card.getName();
        BigInteger number = card.getNumber();
        String expiryDate = card.getExpiryDate();
        int cvv = card.getCVV();

        existingCard.setName(name);
        existingCard.setNumber(number);
        existingCard.setExpiryDate(expiryDate);
        existingCard.setCVV(cvv);

        try {
            cardProducer.sendMessage("Updated card");
        } catch (Exception e) {
            System.out.println(e);
        }
        cardService.updateCard(existingCard);
        return cardService.getAllCards();
    }

    @GetMapping("")
    public List<Card> list(){
        try {
            cardProducer.sendMessage("Listed all cards");
        } catch (Exception e) {
            System.out.println(e);
        }
        return cardService.getAllCards();
    }

    @GetMapping("/{id}")
    public Card getById(@PathVariable("id") int id) {
        try {
            cardProducer.sendMessage("Got card by id");
        } catch (Exception e) {
            System.out.println(e);
        }
        return cardService.getCardById(id);
    }
}
