package com.fruitSalad_backend.payment.service;

import com.fruitSalad_backend.payment.model.Order;
import com.fruitSalad_backend.payment.model.OrderItem;

import java.util.List;

public interface IPaymentService {
    public Order addPayment(Order order);
    public void removePayment(int id);
    public List<Order> updatePayment(Order order);
    public List<Order> getAllPayments();
    public Order getPaymentById(int id);
    public List<OrderItem> getOrderItems();
    public void setOrderItems(List<OrderItem> orderItems);
}
