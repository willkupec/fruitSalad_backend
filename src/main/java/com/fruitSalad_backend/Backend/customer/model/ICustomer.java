package com.fruitSalad_backend.Backend.customer.model;

import com.fruitSalad_backend.Backend.cartItem.model.CartItem;

import java.util.List;

public interface ICustomer {
    public int getId();
    public void setId(int id);
    public String getFirstName();
    public void setFirstName(String firstName);
    public String getLastName();
    public void setLastName(String lastName);
    public String getEmail();
    public void setEmail(String email);
    public String getPassword();
    public void setPassword(String password);
    public List<CartItem> getCart();
    public void setCart(List<CartItem> cart);
    public String toString();
}
