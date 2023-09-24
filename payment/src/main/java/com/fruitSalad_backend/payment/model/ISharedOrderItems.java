package com.fruitSalad_backend.payment.model;

import java.util.List;

public interface ISharedOrderItems {
    public List<OrderItem> getOrderItems();
    public void setOrderItems(List<OrderItem> orderItems);
}
