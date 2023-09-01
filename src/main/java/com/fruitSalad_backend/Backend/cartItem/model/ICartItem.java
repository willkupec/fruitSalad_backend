package com.fruitSalad_backend.Backend.cartItem.model;

public interface ICartItem {
    public int getId();
    public void setId(int id);
    public String getTitle();
    public void setTitle(String title);
    public double getPrice();
    public void setPrice(double price);
    public String getSrc();
    public void setSrc(String src);
    public String toString();
}
