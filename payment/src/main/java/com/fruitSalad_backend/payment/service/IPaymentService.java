package com.fruitSalad_backend.payment.service;

import com.fruitSalad_backend.payment.model.Order;
import com.fruitSalad_backend.payment.model.OrderItem;

import java.util.List;

public interface IPaymentService {
    public Order placeOrder(Order order);
    public void removeOrder(int id);
    public List<Order> updateOrder(Order order);
    public List<Order> getAllOrders();
    public Order getOrderById(int id);
    public List<OrderItem> getOrderItems();
    public void setOrderItems(List<OrderItem> orderItems);
}
