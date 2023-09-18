package com.fruitSalad_backend.Backend.payment.model;

import java.math.BigInteger;
import java.util.List;

public interface IOrder {
    public int getId();
    public void setId(int id);
    public double getTotalPrice();
    public void setTotalPrice(double totalPrice);
    public String getName();
    public void setName(String name);
    public BigInteger getNumber();
    public void setNumber(BigInteger number);
    public String getExpiryDate();
    public void setExpiryDate(String expiryDate);
    public int getCVV();
    public void setCVV(int cvv);
    public String getCustomer();
    public void setCustomer(String customer);
    public List<OrderItem> getOrderItems();
    public void setOrderItems(List<OrderItem> orderItems);
    public String toString();
}
