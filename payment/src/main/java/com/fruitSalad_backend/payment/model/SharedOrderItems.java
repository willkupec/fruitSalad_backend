package com.fruitSalad_backend.payment.model;

import org.springframework.context.annotation.Bean;

import java.util.List;
public class SharedOrderItems implements ISharedOrderItems {

    private List<OrderItem> orderItems;

    @Override
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    @Override
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
