package com.fruitSalad_backend.Backend.model.cartItem;

public interface ICartItem {
    public int getId();
    public void setId(int id);
    public String getName();
    public void setName(String name);
    public double getPrice();
    public void setPrice(double price);
    public String getSize();
    public void setSize(String size);
    public int getQuantity();
    public void setQuantity(int quantity);
}
