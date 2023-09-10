package com.fruitSalad_backend.Backend.payment.service;

import com.fruitSalad_backend.Backend.payment.model.Payment;
import com.fruitSalad_backend.Backend.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public void removePayment(int id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public List<Payment> updatePayment(Payment payment) {
        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(int id) {
        return paymentRepository.findById(id);
    }
}
