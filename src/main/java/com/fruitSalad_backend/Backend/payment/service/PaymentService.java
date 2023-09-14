package com.fruitSalad_backend.Backend.payment.service;

import com.fruitSalad_backend.Backend.payment.model.Order;
import com.fruitSalad_backend.Backend.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Order addPayment(Order order) {
        return paymentRepository.save(order);
    }

    @Override
    public void removePayment(int id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public List<Order> updatePayment(Order order) {
        return paymentRepository.findAll();
    }

    @Override
    public List<Order> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Order getPaymentById(int id) {
        return paymentRepository.findById(id);
    }
}
