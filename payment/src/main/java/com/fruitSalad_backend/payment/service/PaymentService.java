package com.fruitSalad_backend.payment.service;

import com.fruitSalad_backend.payment.model.Order;
import com.fruitSalad_backend.payment.model.OrderItem;
import com.fruitSalad_backend.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService implements IPaymentService {

    private List<OrderItem> orderItems;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Order placeOrder(Order order) {
        return paymentRepository.save(order);
    }

    @Override
    public void removeOrder(int id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public List<Order> updateOrder(Order order) {
        return paymentRepository.findAll();
    }

    @Override
    public List<Order> getAllOrders() {
        return paymentRepository.findAll();
    }

    @Override
    public Order getOrderById(int id) {
        return paymentRepository.findById(id);
    }

    @Override
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    @Override
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
