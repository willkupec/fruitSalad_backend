package com.fruitSalad_backend.payment.model;

public interface IOrderItem {
    public int getId();
    public void setId(int id);
    public int getQuantity();
    public void setQuantity(int quantity);
    public int getCartItemId();
    public void setCartItemId(int cartItemId);
    public String toString();
}
