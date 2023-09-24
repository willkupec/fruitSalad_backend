package com.fruitSalad_backend.payment.model;

public interface IOrderItem {
    public int getId();
    public void setId(int id);
    public String getTitle();
    public void setTitle(String title);
    public double getPrice();
    public void setPrice(double price);
    public int getQuantity();
    public void setQuantity(int quantity);
    public int getCartItemId();
    public void setCartItemId(int cartItemId);
    public String toString();
}
