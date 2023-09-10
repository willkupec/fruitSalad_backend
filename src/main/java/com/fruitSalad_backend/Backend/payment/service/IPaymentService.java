package com.fruitSalad_backend.Backend.payment.service;

import com.fruitSalad_backend.Backend.payment.model.Payment;
import java.util.List;

public interface IPaymentService {
    public Payment addPayment(Payment payment);
    public void removePayment(int id);
    public List<Payment> updatePayment(Payment payment);
    public List<Payment> getAllPayments();
    public Payment getPaymentById(int id);
}
