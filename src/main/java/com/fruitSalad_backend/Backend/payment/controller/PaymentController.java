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

@RequestMapping("/payment")
@RestController
@CrossOrigin
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    PaymentProducer paymentProducer;

    @PostMapping("")
    public String add(@RequestBody Payment payment) {
        try {
            paymentProducer.sendMessage("Added payment");
        } catch (Exception e) {
            System.out.println(e);
        }
        paymentService.addPayment(payment);
        return "Added " + payment.toString();
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") int id) {
        try {
            paymentProducer.sendMessage("Removed payment");
        } catch (Exception e) {
            System.out.println(e);
        }
        paymentService.removePayment(id);
        return "Removed payment with id: " + id;
    }

    @PutMapping("/{id}")
    @Transactional
    public List<Payment> update(@PathVariable int id, @RequestBody Payment payment) throws Exception {
        Payment existingPayment = paymentService.getPaymentById(id);

        if (existingPayment == null) {
            throw new Exception("Payment Not Found");
        }

        String name = payment.getName();
        BigInteger number = payment.getNumber();
        String expiryDate = payment.getExpiryDate();
        int cvv = payment.getCVV();
        String customer = payment.getCustomer();

        existingPayment.setName(name);
        existingPayment.setNumber(number);
        existingPayment.setExpiryDate(expiryDate);
        existingPayment.setCVV(cvv);
        existingPayment.setCustomer(customer);

        try {
            paymentProducer.sendMessage("Updated payment");
        } catch (Exception e) {
            System.out.println(e);
        }
        paymentService.updatePayment(existingPayment);
        return paymentService.getAllPayments();
    }

    @GetMapping("")
    public List<Payment> list(){
        try {
            paymentProducer.sendMessage("Listed all payments");
        } catch (Exception e) {
            System.out.println(e);
        }
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payment getById(@PathVariable("id") int id) {
        try {
            paymentProducer.sendMessage("Got payment by id");
        } catch (Exception e) {
            System.out.println(e);
        }
        return paymentService.getPaymentById(id);
    }
}
