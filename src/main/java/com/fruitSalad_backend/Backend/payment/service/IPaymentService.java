package com.fruitSalad_backend.Backend.payment.service;

import com.fruitSalad_backend.Backend.payment.model.Order;
import java.util.List;

public interface IPaymentService {
    public Order addPayment(Order order);
    public void removePayment(int id);
    public List<Order> updatePayment(Order order);
    public List<Order> getAllPayments();
    public Order getPaymentById(int id);
}
