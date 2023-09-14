package com.fruitSalad_backend.Backend.payment.model;

import com.fruitSalad_backend.Backend.cartItem.model.CartItem;

public interface IOrderItem {
    public int getId();
    public void setId(int id);
    public int getQuantity();
    public void setQuantity(int quantity);
    public CartItem getCartItem();
    public void setCartItem(CartItem cartItem);
    public String toString();
}
