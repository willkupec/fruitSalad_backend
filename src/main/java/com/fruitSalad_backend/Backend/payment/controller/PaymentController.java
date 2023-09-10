package com.fruitSalad_backend.Backend.payment.controller;

import com.fruitSalad_backend.Backend.payment.messaging.PaymentProducer;
import com.fruitSalad_backend.Backend.payment.model.Payment;
import com.fruitSalad_backend.Backend.payment.repository.PaymentRepository;
import com.fruitSalad_backend.Backend.payment.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RequestMapping("/card")
@RestController
@CrossOrigin
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private IPaymentService cardService;

    @Autowired
    PaymentProducer paymentProducer;

    @PostMapping("")
    public String add(@RequestBody Payment payment) {
        try {
            paymentProducer.sendMessage("Added card");
        } catch (Exception e) {
            System.out.println(e);
        }
        cardService.addCard(payment);
        return "Added " + payment.toString();
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") int id) {
        try {
            paymentProducer.sendMessage("Removed card");
        } catch (Exception e) {
            System.out.println(e);
        }
        cardService.removeCard(id);
        return "Removed card with id: " + id;
    }

    @PutMapping("/{id}")
    @Transactional
    public List<Payment> update(@PathVariable int id, @RequestBody Payment payment) throws Exception {
        Payment existingPayment = cardService.getCardById(id);

        if (existingPayment == null) {
            throw new Exception("Card Not Found");
        }

        String name = payment.getName();
        BigInteger number = payment.getNumber();
        String expiryDate = payment.getExpiryDate();
        int cvv = payment.getCVV();

        existingPayment.setName(name);
        existingPayment.setNumber(number);
        existingPayment.setExpiryDate(expiryDate);
        existingPayment.setCVV(cvv);

        try {
            paymentProducer.sendMessage("Updated card");
        } catch (Exception e) {
            System.out.println(e);
        }
        cardService.updateCard(existingPayment);
        return cardService.getAllCards();
    }

    @GetMapping("")
    public List<Payment> list(){
        try {
            paymentProducer.sendMessage("Listed all cards");
        } catch (Exception e) {
            System.out.println(e);
        }
        return cardService.getAllCards();
    }

    @GetMapping("/{id}")
    public Payment getById(@PathVariable("id") int id) {
        try {
            paymentProducer.sendMessage("Got card by id");
        } catch (Exception e) {
            System.out.println(e);
        }
        return cardService.getCardById(id);
    }
}
